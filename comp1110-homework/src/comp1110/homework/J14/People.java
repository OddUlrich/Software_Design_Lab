package comp1110.homework.J14;

import java.util.HashMap;
import java.util.Scanner;

public class People {
    public static void main(String[] args) {
        HashMap<String, Integer> peopleMap = new HashMap<>();
        Scanner in = new Scanner(System.in);
        String input;
        String[] tmp;
        int age;

        while(in.hasNext()) {
            input = in.nextLine();
            tmp = input.split(" ");

            if (tmp.length == 2) {
                peopleMap.put(tmp[0], Integer.parseInt(tmp[1]));
            } else if (tmp.length == 1) {
                age = peopleMap.getOrDefault(tmp[0], 0);
                if (age == 0) {
                    System.out.println("unknown");
                } else {
                    System.out.println(age);
                }
            }
        }
    }
}
