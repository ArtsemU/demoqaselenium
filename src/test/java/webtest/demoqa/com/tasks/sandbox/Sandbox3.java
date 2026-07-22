package webtest.demoqa.com.tasks.sandbox;

import java.util.List;
import java.util.stream.Collectors;

public class Sandbox3 {
    public static void main(String[] args) {
        // Streams

        List<Integer> ages =  List.of(20, 31, 22, 18);
        List<Integer> filtered = ages.stream()
                .filter(num -> num > 20)
                .collect(Collectors.toList());
        System.out.println(filtered);
    }
}
