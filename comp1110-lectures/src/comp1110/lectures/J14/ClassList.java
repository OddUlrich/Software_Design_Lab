package comp1110.lectures.J14;

import comp1110.lectures.O02.COMP1110Student;

import java.util.*;

public class ClassList {
    public static void main(String[] args) {
        COMP1110Student stuarray[] = {
                new COMP1110Student("Mary", 19, "u2345678", 4, 15, 5, 4, 5, 4, 70),
                new COMP1110Student("Josh", 40, "u6678901", 5, 17, 4, 5, 3, 4, 69),
                new COMP1110Student("Bob", 20, "u3456789", 3, 16, 4, 5, 4, 5, 75),
                new COMP1110Student("Ting", 21, "u4567890", 4, 14, 4, 4, 5, 5, 90),
                new COMP1110Student("Vijay", 18, "u5678901", 5, 17, 4, 5, 3, 4, 69),
        };

        for (var student : stuarray) {
            System.out.println("From array: " + student);
        }

        List<COMP1110Student> students = new ArrayList<>(Arrays.asList(stuarray));

        for (var student : students) {
            System.out.println("From list: " + student);
        }
        var newArray = students.toArray(new COMP1110Student[students.size()]);

        Map<String, COMP1110Student> uidMap = new HashMap<>();
        for (var student : students) {
            uidMap.put(student.getUID(), student);
        }

        var ting = uidMap.get("u4567890");
        System.out.println("Found a match: " + ting);
        uidMap.remove("u4567890");

        Collections.sort(students);
        for (var student : students) {
            System.out.println("Natural sort order: " + student);
        }

        /*
        Comparator<COMP1110Student> nameComparator = (s1, s2) ->
                s1.getName().compareTo(s2.getName());
        */
        Collections.sort(students, (s1, s2) ->
                s1.getName().compareTo(s2.getName()));
        for (var student : students) {
            System.out.println("Sorted by name: " + student);
        }
    }
}
