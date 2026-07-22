package webtest.demoqa.com.tasks.sandbox;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Sandbox2 {
    public static void main(String[] args) {
        String t = "";
        System.out.println(t);
        String revert = reverting(t);
        System.out.println(revert);
        boolean isPal = isPalindrome(t);
        System.out.println("Is palindrome : " + isPal);

        Map<Character, Integer> map1 = new HashMap<>();
        map1.put('a', 1);
        map1.put('q', 9);
        map1.put('b', 2);
        map1.put('c', 3);
        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        for (char c : map1.keySet()) {
            System.out.println(c + " = " + map1.get(c));
        }
        test1("hello");
        secondLargest2(new int[]{1, 5, 7, 6, 7});
        uniqueElement(new int[]{1, 5, 7, 6, 7, 1});
        revertArr(new int[]{1, 2, 3, 4, 5});
    }
    public static String reverting(String text) {
        if (text.length() < 2) {
            return text;
        }
        char[] chars = text.toCharArray();
        int first = 0;
        int last = chars.length - 1;
        while (first < last) {
            char tmp = chars[first];
            chars[first] = chars[last];
            chars[last] = tmp;
            first++;
            last--;
        }
        return String.valueOf(chars);
    }

    public static boolean isPalindrome(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        char[] chars = text.toCharArray();
        int first = 0;
        int last = chars.length - 1;
        while (first < last) {
            if(chars[first] != chars[last]) {
                return false;
            }
            first++;
            last--;
        }
        return true;
    }

    public static void test1(String text1) {
        int[] frq = new int[256];

        for(int i = 0; i < text1.length(); i++) {
            frq[text1.charAt(i)]++;
        }
        for (int i = 0; i < frq.length; i++) {
            if(frq[i] != 0) {
                System.out.println("char i :" + (char) i + ", value : " + frq[i]);
            }
        }
    }

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

    public static void secondLargest2(int[] arr) {
        System.out.println("Second Largest");
        int first = arr[0];
        int second = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > first) {
                second = first;
                first = arr[i];
            } else if (arr[i] > second && arr[i] != first) {
                second = arr[i];
            }
        }
        System.out.println("Fisrt : " + first);
        System.out.println("Second : " + second);
    }

    public static void uniqueElement(int[] arr) {
        Set<Integer> elements = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();
        for(int n : arr) {
            if(!elements.add(n)) {
                duplicates.add(n);
            }
        }
        for(int n : duplicates) {
            System.out.println("duplicates :" + n);
        }
    }

    public static void revertArr(int[] arr) {
        int first = 0;
        int last = arr.length - 1;
        while (first < last) {
            int tmp = arr[last];
            arr[last] = arr[first];
            arr[first] = tmp;
            first++;
            last--;
        }
        for(int n : arr) {
            System.out.print(n + " ,");
        }
    }
}
