package interview;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LEVEL 2 — Arrays & Collections
 * Common data-structure tasks for Java QA interviews.
 */
public class Level2_ArraysAndCollections {

    // ── Task 1 ─────────────────────────────────────────────────────────────
    // Find duplicate elements in an array
    // {1,2,3,2,4,3,5} → [2, 3]
    public static List<Integer> findDuplicates(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new LinkedHashSet<>();
        for (int n : arr) {
            if (!seen.add(n)) duplicates.add(n);
        }
        return new ArrayList<>(duplicates);
    }

    // ── Task 2 ─────────────────────────────────────────────────────────────
    // Find second largest number in an array (no sorting)
    // {3, 1, 4, 1, 5, 9, 2, 6} → 6
    public static int secondLargest(int[] arr) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int n : arr) {
            if (n > first) {
                second = first;
                first = n;
            } else if (n > second && n != first) {
                second = n;
            }
        }
        return second;
    }

    // ── Task 3 ─────────────────────────────────────────────────────────────
    // Reverse an array in-place
    // {1, 2, 3, 4, 5} → {5, 4, 3, 2, 1}
    public static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int tmp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = tmp;
        }
    }

    // ── Task 4 ─────────────────────────────────────────────────────────────
    // Sort a List of strings by LENGTH, then alphabetically for same length
    // ["banana", "apple", "fig", "kiwi"] → [fig, kiwi, apple, banana]
    public static List<String> sortByLength(List<String> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
    }

    public static List<String> sortByLength_withoutStreams(List<String> list) {
        List<String> result = new ArrayList<>(list);
        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                if (a.length() != b.length()) return Integer.compare(a.length(), b.length());
                return a.compareTo(b);
            }
        });
        return result;
    }

    // ── Task 5 ─────────────────────────────────────────────────────────────
    // Given a list of integers, return only the even numbers, sorted ascending
    // [5, 2, 8, 1, 4, 7, 6] → [2, 4, 6, 8]
    public static List<Integer> filterEvens(List<Integer> list) {
        return list.stream()
                .filter(n -> n % 2 == 0)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Integer> filterEvens_withoutStreams(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (Integer n : list) {
            if (n % 2 == 0) result.add(n);
        }
        Collections.sort(result);
        return result;
    }

    // ── Task 6 ─────────────────────────────────────────────────────────────
    // Count frequency of each element using a Map
    // ["a","b","a","c","b","a"] → {a=3, b=2, c=1}
    public static Map<String, Integer> frequency(List<String> list) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String s : list) map.merge(s, 1, Integer::sum);
        return map;
    }

    // ── Task 7 ─────────────────────────────────────────────────────────────
    // Find intersection of two lists (common elements, no duplicates)
    // [1,2,3,4], [3,4,5,6] → [3, 4]
    public static List<Integer> intersection(List<Integer> a, List<Integer> b) {
        Set<Integer> setB = new HashSet<>(b);
        return a.stream().filter(setB::contains).distinct().collect(Collectors.toList());
    }

    public static List<Integer> intersection_withoutStreams(List<Integer> a, List<Integer> b) {
        Set<Integer> setB = new HashSet<>(b);
        List<Integer> result = new ArrayList<>();
        Set<Integer> added = new HashSet<>();
        for (Integer n : a) {
            if (setB.contains(n) && added.add(n)) {
                result.add(n);
            }
        }
        return result;
    }

    // ── Task 8 ─────────────────────────────────────────────────────────────
    // Remove duplicates from a List while preserving order
    // [3, 1, 4, 1, 5, 9, 2, 6, 5] → [3, 1, 4, 5, 9, 2, 6]
    public static <T> List<T> removeDuplicates(List<T> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }

    public static <T> List<T> removeDuplicates_withoutStreams(List<T> list) {
        List<T> result = new ArrayList<>();
        Set<T> seen = new HashSet<>();
        for (T t : list) {
            if (seen.add(t)) result.add(t);
        }
        return result;
    }

    // ── Task 9 ─────────────────────────────────────────────────────────────
    // Check if a string of brackets is balanced, using a Stack
    // "{[()]}" → true   "{[(])}" → false   "(()" → false
    public static boolean isBalanced(String expr) {
        Stack<Character> stack = new Stack<>();
        for (char c : expr.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char open = stack.pop();
                if ((c == ')' && open != '(') || (c == ']' && open != '[') || (c == '}' && open != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // ── Task 10 ────────────────────────────────────────────────────────────
    // First non-repeating character in a stream, using a Queue
    // "aabc" → [a, #, b, b]   ('#' means no non-repeating character yet)
    public static List<Character> firstNonRepeatingInStream(String stream) {
        Map<Character, Integer> freq = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        List<Character> result = new ArrayList<>();
        for (char c : stream.toCharArray()) {
            freq.merge(c, 1, Integer::sum);
            queue.add(c);
            while (!queue.isEmpty() && freq.get(queue.peek()) > 1) {
                queue.poll();
            }
            result.add(queue.isEmpty() ? '#' : queue.peek());
        }
        return result;
    }

    // ── Task 11 ────────────────────────────────────────────────────────────
    // Check if a String is a palindrome, using a Deque
    // "level" → true   "hello" → false
    public static boolean isPalindrome(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) deque.addLast(c);
        while (deque.size() > 1) {
            if (!deque.pollFirst().equals(deque.pollLast())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("=== LEVEL 2: Arrays & Collections ===\n");

        System.out.println("Task 1 - Find duplicates:");
        System.out.println(findDuplicates(new int[]{1, 2, 3, 2, 4, 3, 5})); // [2, 3]

        System.out.println("\nTask 2 - Second largest:");
        System.out.println(secondLargest(new int[]{3, 1, 4, 1, 5, 9, 2, 6})); // 6

        System.out.println("\nTask 3 - Reverse array:");
        int[] arr = {1, 2, 3, 4, 5};
        reverseArray(arr);
        System.out.println(Arrays.toString(arr)); // [5, 4, 3, 2, 1]

        System.out.println("\nTask 4 - Sort by length:");
        System.out.println(sortByLength(Arrays.asList("banana", "apple", "fig", "kiwi")));
        // [fig, kiwi, apple, banana]
        System.out.println(sortByLength_withoutStreams(Arrays.asList("banana", "apple", "fig", "kiwi")));
        // [fig, kiwi, apple, banana]

        System.out.println("\nTask 5 - Filter evens:");
        System.out.println(filterEvens(Arrays.asList(5, 2, 8, 1, 4, 7, 6))); // [2, 4, 6, 8]
        System.out.println(filterEvens_withoutStreams(Arrays.asList(5, 2, 8, 1, 4, 7, 6))); // [2, 4, 6, 8]

        System.out.println("\nTask 6 - Frequency map:");
        System.out.println(frequency(Arrays.asList("a", "b", "a", "c", "b", "a"))); // {a=3, b=2, c=1}

        System.out.println("\nTask 7 - Intersection:");
        System.out.println(intersection(
                Arrays.asList(1, 2, 3, 4), Arrays.asList(3, 4, 5, 6))); // [3, 4]
        System.out.println(intersection_withoutStreams(
                Arrays.asList(1, 2, 3, 4), Arrays.asList(3, 4, 5, 6))); // [3, 4]

        System.out.println("\nTask 8 - Remove duplicates:");
        System.out.println(removeDuplicates(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5)));
        // [3, 1, 4, 5, 9, 2, 6]
        System.out.println(removeDuplicates_withoutStreams(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5)));
        // [3, 1, 4, 5, 9, 2, 6]

        System.out.println("\nTask 9 - Balanced brackets (Stack):");
        System.out.println(isBalanced("{[()]}")); // true
        System.out.println(isBalanced("{[(])}")); // false
        System.out.println(isBalanced("(("));     // false

        System.out.println("\nTask 10 - First non-repeating in stream (Queue):");
        System.out.println(firstNonRepeatingInStream("aabc")); // [a, #, b, b]

        System.out.println("\nTask 11 - Palindrome check (Deque):");
        System.out.println(isPalindrome("level")); // true
        System.out.println(isPalindrome("hello")); // false
    }
}
