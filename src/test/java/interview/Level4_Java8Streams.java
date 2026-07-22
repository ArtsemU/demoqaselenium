package interview;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * LEVEL 4 — Java 8: Streams, Lambdas, Optional, Functional Interfaces
 *
 * This is the most tested modern-Java area for QA engineers.
 * Every task below is a real interview question with a real answer.
 */
public class Level4_Java8Streams {

    // ── Task 1 ─────────────────────────────────────────────────────────────
    // Sum of all even numbers in a list using streams
    // [1,2,3,4,5,6] → 12
    public static int sumOfEvens(List<Integer> list) {
        return list.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    // ── Task 2 ─────────────────────────────────────────────────────────────
    // Convert a list of strings to uppercase and sort them
    // ["banana","apple","fig"] → [APPLE, BANANA, FIG]
    public static List<String> upperSorted(List<String> list) {
        return list.stream()
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }

    // ── Task 3 ─────────────────────────────────────────────────────────────
    // Group a list of strings by their first character
    // ["apple","avocado","banana","blueberry"] → {a=[apple, avocado], b=[banana, blueberry]}
    public static Map<Character, List<String>> groupByFirstChar(List<String> list) {
        return list.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0)));
    }

    // ── Task 4 ─────────────────────────────────────────────────────────────
    // Find the longest string in a list using Optional
    // ["hi","hello","hey"] → "hello"
    public static Optional<String> longestString(List<String> list) {
        return list.stream()
                .max(Comparator.comparingInt(String::length));
    }

    // ── Task 5 ─────────────────────────────────────────────────────────────
    // Flatten a list of lists into a single list
    // [[1,2],[3,4],[5]] → [1,2,3,4,5]
    public static List<Integer> flatten(List<List<Integer>> lists) {
        return lists.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    // ── Task 6 ─────────────────────────────────────────────────────────────
    // Partition a list into two groups: pass/fail based on score >= 50
    // scores → {true=[70,90,55], false=[30,45]}
    public static Map<Boolean, List<Integer>> partitionByScore(List<Integer> scores) {
        return scores.stream()
                .collect(Collectors.partitioningBy(s -> s >= 50));
    }

    // ── Task 7: Functional interfaces ──────────────────────────────────────
    // Write a validator using Predicate, then chain two predicates with AND
    public static void predicateDemo() {
        Predicate<String> notEmpty = s -> !s.isEmpty();
        Predicate<String> longerThan3 = s -> s.length() > 3;
        Predicate<String> validInput = notEmpty.and(longerThan3);

        System.out.println(validInput.test(""));       // false
        System.out.println(validInput.test("hi"));     // false
        System.out.println(validInput.test("hello"));  // true
    }

    // ── Task 8: Method references ──────────────────────────────────────────
    // Four types: static, instance (unbound), instance (bound), constructor
    public static void methodReferenceDemo() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // static ref
        names.forEach(System.out::println);

        // unbound instance ref (method called on each element)
        names.stream().map(String::toUpperCase).forEach(System.out::println);

        // bound instance ref (method called on a specific object)
        String prefix = "User: ";
        Function<String, String> addPrefix = prefix::concat;
        names.stream().map(addPrefix).forEach(System.out::println);
    }

    // ── Task 9: Optional — avoid NullPointerException ──────────────────────
    // Classic NPE trap interviewers love. Return username or "Guest" if null.
    public static String getUsername(String raw) {
        return Optional.ofNullable(raw)
                .filter(s -> !s.isBlank())
                .map(String::trim)
                .orElse("Guest");
    }

    // ── Task 10: Collecting statistics ─────────────────────────────────────
    // Get min/max/avg/count/sum from a list in one pass
    public static void statisticsDemo(List<Integer> scores) {
        IntSummaryStatistics stats = scores.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();

        System.out.println("Count : " + stats.getCount());
        System.out.println("Min   : " + stats.getMin());
        System.out.println("Max   : " + stats.getMax());
        System.out.println("Sum   : " + (long) stats.getSum());
        System.out.printf ("Avg   : %.2f%n", stats.getAverage());
    }

    public static void main(String[] args) {
        System.out.println("=== LEVEL 4: Java 8 Streams & Lambdas ===\n");

        System.out.println("Task 1 - Sum of evens:");
        System.out.println(sumOfEvens(Arrays.asList(1, 2, 3, 4, 5, 6))); // 12

        System.out.println("\nTask 2 - Upper sorted:");
        System.out.println(upperSorted(Arrays.asList("banana", "apple", "fig")));
        // [APPLE, BANANA, FIG]

        System.out.println("\nTask 3 - Group by first char:");
        System.out.println(groupByFirstChar(Arrays.asList("apple","avocado","banana","blueberry")));

        System.out.println("\nTask 4 - Longest string:");
        System.out.println(longestString(Arrays.asList("hi", "hello", "hey"))); // Optional[hello]

        System.out.println("\nTask 5 - Flatten:");
        System.out.println(flatten(Arrays.asList(
                Arrays.asList(1, 2), Arrays.asList(3, 4), Collections.singletonList(5))));
        // [1, 2, 3, 4, 5]

        System.out.println("\nTask 6 - Partition by score:");
        System.out.println(partitionByScore(Arrays.asList(70, 30, 90, 45, 55)));

        System.out.println("\nTask 7 - Predicate chain:");
        predicateDemo();

        System.out.println("\nTask 8 - Method references:");
        methodReferenceDemo();

        System.out.println("\nTask 9 - Optional:");
        System.out.println(getUsername(null));    // Guest
        System.out.println(getUsername("  "));   // Guest
        System.out.println(getUsername(" Alice")); // Alice

        System.out.println("\nTask 10 - Statistics:");
        statisticsDemo(Arrays.asList(70, 30, 90, 45, 55, 100, 20));
    }
}
