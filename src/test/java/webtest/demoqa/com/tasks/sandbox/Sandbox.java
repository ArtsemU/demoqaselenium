package webtest.demoqa.com.tasks.sandbox;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Sandbox {
    public static void main(String[] args) {

        int[] nums = {1, 2, 3};
        rotateLeft3(nums);

    }
    public static String lastChars(String a, String b) {
        a = a + "@";
        if (b.length() == 0) {
            b = b + "@";
        }
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("" + a.charAt(0) + b.charAt(b.length() - 1));
        return "" + a.charAt(0) + b.charAt(b.length() - 1);
    }
    public static String minCat(String a, String b) {
        if (a.length() == b.length()) {
            return a + b;
        }
        return a.length() > b.length() ?
                a.substring(a.length() - b.length()) + b :
                a + b.substring(b.length() - a.length());

    }

    public String deFront(String str) {
        String front = "";
        if (str.isEmpty()) {
            return "";
        }
        if (str.length() == 1 && str.charAt(0) == 'a') {
            front = front + 'a';
        }
        if (str.length() == 2 && str.charAt(1) == 'b') {
            front = front + 'b';
        }
        return front + str.substring(2);
    }

    public static int[] rotateLeft3(int[] nums) {
        int[] res = new int[nums.length];
        int offset = 12;
        offset = offset % nums.length;

        for (int i = 0; i < nums.length; i++) {
            int newIndex = (i + offset + nums.length) % nums.length;
            System.out.println("Index __ offset __ nums.length __ total __ % length");
            System.out.println(i + "     __   " + offset + "   __    " +  nums.length + "      __   " + (i + offset + nums.length));
           // System.out.println("index = " + i + " || new index = " + newIndex);
            res[newIndex] = nums[i];
        }
        System.out.println("[" + res[0] + "," + res[1] + "," + res[2] + "]");
        return res;
    }
    public Map<String, Integer> word0(String[] strings) {
        Map<String, Integer> res = new HashMap<String, Integer>();

        for(String s : strings) {
            res.put(s, res.getOrDefault(s, 0) + 1);
        }
        return res;
    }
    public Map<String, String> firstChar(String[] strings) {
        Map<String, String> res = new HashMap<String, String>();
        for(String s : strings) {
            res.put("" + s.charAt(0), res.getOrDefault("" + s.charAt(0), "") + s);
        }
        return res;
    }

    public String wordAppend(String[] strings) {
        Map<String, Integer> frq = new HashMap<String, Integer>();
        Set<String> set = new HashSet<>();
        for(String s: strings) {
            set.add(s);
            frq.put(s, frq.getOrDefault(s, 0) + 1);
        }
        String res = "";
        for(Object s : set) {
            if(frq.get(s) % 2 == 0) {
                res = res + s;
            }
        }
        return res;
    }

    public String[] firstSwap(String[] strings) {
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> unique = new HashSet();
        for (int i = 0; i < strings.length; i++) {
            char ch = strings[i].charAt(0);

            if (map.containsKey(ch) && !unique.contains(ch)) {
                int index = map.get(ch);

                String temp = strings[i];
                strings[i] = strings[index];
                strings[index] = temp;

                map.remove(ch);
                unique.add(ch);
            } else {
                map.put(ch, i);
            }
        }

        return strings;
    }

}
