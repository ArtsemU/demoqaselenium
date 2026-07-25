package interview;

import java.util.*;
import java.util.stream.*;

/**
 * LEVEL 7 — Stream Creation & Intermediate Operations
 *
 * Follow-up to {@link Level4_Java8Streams}. That class shows streams solving
 * small tasks; this one is a method-by-method catalog of how a Stream
 * *pipeline* is built: where the data comes from, and every intermediate
 * operation you can chain before a terminal operation runs it.
 *
 * Intermediate operations are LAZY — nothing happens until a terminal
 * operation (forEach, collect, reduce, count, ...) is called.
 * See {@link Level8_StreamCollectors} for terminal operations & Collectors.
 */
public class Level7_StreamOperations {

    // ── 1. Creating streams from collections ────────────────────────────────
    public static void creationFromCollections() {
        List<String> list = List.of("a", "b", "c");
        Stream<String> fromList = list.stream();

        Set<String> set = Set.of("x", "y", "z");
        Stream<String> fromSet = set.stream();

        Map<String, Integer> map = Map.of("one", 1, "two", 2);
        Stream<Map.Entry<String, Integer>> fromMap = map.entrySet().stream();

        System.out.println(fromList.collect(Collectors.joining(",")));
        System.out.println(fromSet.collect(Collectors.joining(",")));
        fromMap.forEach(e -> System.out.println(e.getKey() + "=" + e.getValue()));
    }

    // ── 2. Creating streams without a collection ────────────────────────────
    public static void creationWithoutCollections() {
        Stream<Integer> ofValues = Stream.of(1, 2, 3);
        System.out.println(ofValues.collect(Collectors.toList())); // [1, 2, 3]

        // finite: iterate with a stop condition (Java 9+)
        List<Integer> powersOfTwo = Stream.iterate(1, n -> n < 100, n -> n * 2)
                .collect(Collectors.toList());
        System.out.println(powersOfTwo); // [1, 2, 4, 8, 16, 32, 64]

        // infinite generator, bounded with limit()
        List<Double> randoms = Stream.generate(Math::random)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(randoms.size()); // 3

        // primitive streams: no boxing, extra numeric methods
        int[] arr = IntStream.rangeClosed(1, 5).toArray();
        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5]
    }

    // ── 3. filter — keep elements matching a predicate ──────────────────────
    // ["ant","bee","cat","dog"] → words longer than 2 chars starting with vowel-free... simplified:
    public static List<String> filterDemo(List<String> words) {
        return words.stream()
                .filter(w -> w.length() > 2)
                .collect(Collectors.toList());
    }

    // ── 4. map — transform each element 1-to-1 ──────────────────────────────
    // [1,2,3] → [1,4,9] (squares)
    public static List<Integer> mapDemo(List<Integer> nums) {
        return nums.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
    }

    // ── 5. flatMap — flatten nested collections into one stream ─────────────
    // {"Alice": ["Java","SQL"], "Bob": ["Python"]} → ["Java","SQL","Python"]
    public static List<String> flatMapDemo(Map<String, List<String>> skillsByPerson) {
        return skillsByPerson.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    // ── 6. distinct — remove duplicates (uses equals/hashCode) ──────────────
    public static List<Integer> distinctDemo(List<Integer> nums) {
        return nums.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    // ── 7. sorted — natural order or a custom Comparator ─────────────────────
    public static List<String> sortedDemo(List<String> words) {
        return words.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
    }

    // ── 8. limit / skip — pagination-style slicing ───────────────────────────
    // page 2, size 3 of a 10-element stream → elements[3..5]
    public static List<Integer> pageDemo(List<Integer> items, int pageNumber, int pageSize) {
        return items.stream()
                .skip((long) (pageNumber - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    // ── 9. peek — observe elements mid-pipeline (debugging only, no side effects in prod code) ──
    public static List<Integer> peekDemo(List<Integer> nums) {
        return nums.stream()
                .peek(n -> System.out.println("  before filter: " + n))
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("  after filter : " + n))
                .collect(Collectors.toList());
    }

    // ── 10. Chaining several intermediate ops in one pipeline ───────────────
    // Typical QA use case: parse raw log lines, keep only ERROR lines,
    // extract the message, dedupe, sort.
    public static List<String> errorMessagesDemo(List<String> logLines) {
        return logLines.stream()
                .filter(line -> line.startsWith("ERROR"))
                .map(line -> line.substring(line.indexOf(':') + 1).trim())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println("=== LEVEL 7: Stream Creation & Intermediate Operations ===\n");

        System.out.println("1 - Creation from collections:");
        creationFromCollections();

        System.out.println("\n2 - Creation without collections:");
        creationWithoutCollections();

        System.out.println("\n3 - filter:");
        System.out.println(filterDemo(List.of("ant", "bee", "cat", "dog"))); // [ant, bee, cat, dog] (all len 3)

        System.out.println("\n4 - map:");
        System.out.println(mapDemo(List.of(1, 2, 3))); // [1, 4, 9]

        System.out.println("\n5 - flatMap:");
        Map<String, List<String>> skills = new LinkedHashMap<>();
        skills.put("Alice", List.of("Java", "SQL"));
        skills.put("Bob", List.of("Python"));
        System.out.println(flatMapDemo(skills)); // [Java, SQL, Python]

        System.out.println("\n6 - distinct:");
        System.out.println(distinctDemo(List.of(1, 2, 2, 3, 3, 3))); // [1, 2, 3]

        System.out.println("\n7 - sorted (by length, then alphabetically):");
        System.out.println(sortedDemo(List.of("bb", "a", "ccc", "dd"))); // [a, bb, dd, ccc]

        System.out.println("\n8 - limit/skip (page 2, size 3):");
        System.out.println(pageDemo(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 2, 3)); // [4, 5, 6]

        System.out.println("\n9 - peek:");
        System.out.println(peekDemo(List.of(1, 2, 3, 4)));

        System.out.println("\n10 - chained pipeline (log parsing):");
        System.out.println(errorMessagesDemo(List.of(
                "INFO: server started",
                "ERROR: timeout on /login",
                "ERROR: timeout on /login",
                "ERROR: connection refused"
        )));
        // [connection refused, timeout on /login]
    }
}
