package sandbox;

import java.sql.Array;
import java.util.HashMap;
import java.util.Map;

public class SimpleTask {

    public static void main(String[] args) {

        // #1 Развернуть строку без reverse()
        String text = "Arty";
        String result = "";

        for(int i = text.length() - 1; i >=0; i--) {
            result = result + text.charAt(i);
        }
        System.out.println("Init String   : " + text);
        System.out.println("Revert String : " + result);

        // Проверить является ли строка палиндромом
        String polindrome = "abcba";
        String polindromeNot = "abcbx";

        String s1 = "Hello";
        String s2 = "Hello";
        s2 = s2 + "hi!";
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);

        String text1 = "abcdeabcabcaba";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : text1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : map.keySet()) {
            System.out.println("Key : " + c + ", value : " + map.get(c) );
        }

        System.out.println("=====================");
        Integer a = new Integer(3);
        Integer b = new Integer(3);
        System.out.println(a == b);



    }

}
