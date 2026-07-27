package webtest.demoqa.com.tasks.sandbox;

import java.util.*;
import java.util.stream.Collectors;

public class Sandbox3 {
    public static void main(String[] args) {
        // Streams

        List<Integer> ages =  List.of(20, 31, 22, 18);
        List<Integer> filtered = ages.stream()
                .filter(num -> num > 20)
                .collect(Collectors.toList());
        System.out.println(filtered);

        List<Student> students = new ArrayList<>();
        students.add(new Student("Mark", 66));
        students.add(new Student("Max", 83));
        students.add(new Student("Jane", 79));
        students.add(new Student("Maria", 48));
        students.add(new Student("Oliver", 56));
        students.add(new Student("Jhon", 30));

        Set<Student> resutl = students.stream()
                .filter(student -> student.getResult() > 60)
                .collect(Collectors.toSet());

        System.out.println(resutl);

        Set<String> resutlName = students.stream()
                .filter(student -> student.getResult() > 60)
                .map(Student::getName) // .map(student -> student.getName())
                .peek(n -> System.out.println(n))
                .filter(name -> name.startsWith("M"))
                .collect(Collectors.toSet());
        System.out.println(resutlName);

        List<String> testMethod = students.stream()
                .filter(p -> p.getResult() >= 40)
                .sorted(Comparator.comparing(Student::getResult))
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println(testMethod);

        List<Student> scoreList = students.stream()
                .filter(p -> p.getResult() > 20)
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
        System.out.println(scoreList);

        Map<String, Integer> scores = new HashMap<>();
        scores.put("Ivan", 85);
        scores.put("Anna", 92);
        scores.put("Max", 78);

        Set<Map.Entry<String, Integer>> entries = scores.entrySet();   // получили Set

        for (Map.Entry<String, Integer> entry : entries) {
            if (entry.getKey().equals("Anna")) {
                System.out.println("Нашли Anna, результат: " + entry.getValue());
            }
        }
        System.out.println("////////////");
        for(Map.Entry<String, Integer> e : entries) {
            if(e.getValue() > 20) {
                System.out.println(e.getKey());
            }
        }
    }
}
