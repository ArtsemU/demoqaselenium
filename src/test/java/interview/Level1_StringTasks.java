package interview;

/**
 * LEVEL 1 — Strings
 * Classic string tasks you WILL face in Java QA interviews.
 * Run each method from main() and verify the output mentally first.
 */
public class Level1_StringTasks {

    // ── Task 1 ─────────────────────────────────────────────────────────────
    // Reverse a string WITHOUT using StringBuilder.reverse()
    // Input: "hello"  → Output: "olleh"
    public static String reverseString(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
        return new String(chars);
    }

    // ── Task 2 ─────────────────────────────────────────────────────────────
    // Check if a string is a palindrome (reads same forwards and backwards)
    // "racecar" → true,  "hello" → false
    public static boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-z0-9]", ""); // ignore spaces/punctuation
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // ── Task 3 ─────────────────────────────────────────────────────────────
    // Check if two strings are anagrams (same letters, different order)
    // "listen" / "silent" → true,  "hello" / "world" → false
    public static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        int[] counts = new int[256];
        for (char c : a.toCharArray()) counts[c]++;
        for (char c : b.toCharArray()) counts[c]--;
        for (int count : counts) if (count != 0) return false;
        return true;
    }

    // ── Task 4 ─────────────────────────────────────────────────────────────
    // Find the first non-repeating character in a string
    // "aabbcde" → 'c'
    public static char firstNonRepeating(String s) {
        int[] freq = new int[256];
        for (char c : s.toCharArray()) freq[c]++;
        for (char c : s.toCharArray()) if (freq[c] == 1) return c;
        return '\0'; // no unique char found
    }

    // ── Task 5 ─────────────────────────────────────────────────────────────
    // Count occurrences of each character (return as formatted string)
    // "aabbc" → "a=2, b=2, c=1"
    public static String charFrequency(String s) {
        int[] freq = new int[256];
        for (char c : s.toCharArray()) freq[c]++;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                if (sb.length() > 0) sb.append(", ");
                sb.append((char) i).append("=").append(freq[i]);
            }
        }
        return sb.toString();
    }

    // ── Task 6 ─────────────────────────────────────────────────────────────
    // Count words in a sentence (handle multiple spaces)
    // "  hello   world  " → 2
    public static int wordCount(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) return 0;
        return sentence.trim().split("\\s+").length;
    }

    // ── Task 7 ─────────────────────────────────────────────────────────────
    // Check if a string contains only digits
    // "12345" → true,  "123a5" → false
    public static boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("=== LEVEL 1: String Tasks ===\n");

        System.out.println("Task 1 - Reverse:");
        System.out.println(reverseString("hello"));        // olleh
        System.out.println(reverseString("OpenAI"));       // IAnepO

        System.out.println("\nTask 2 - Palindrome:");
        System.out.println(isPalindrome("racecar"));       // true
        System.out.println(isPalindrome("A man a plan a canal Panama")); // true
        System.out.println(isPalindrome("hello"));         // false

        System.out.println("\nTask 3 - Anagram:");
        System.out.println(isAnagram("listen", "silent")); // true
        System.out.println(isAnagram("hello", "world"));   // false

        System.out.println("\nTask 4 - First non-repeating char:");
        System.out.println(firstNonRepeating("aabbcde"));  // c
        System.out.println(firstNonRepeating("aabb"));     // (null char)

        System.out.println("\nTask 5 - Char frequency:");
        System.out.println(charFrequency("aabbc"));        // a=2, b=2, c=1

        System.out.println("\nTask 6 - Word count:");
        System.out.println(wordCount("  hello   world  ")); // 2

        System.out.println("\nTask 7 - Is numeric:");
        System.out.println(isNumeric("12345"));             // true
        System.out.println(isNumeric("123a5"));             // false
    }
}
