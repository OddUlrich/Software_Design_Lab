package comp1110.homework.J05;

import java.util.Scanner;

public class Movie {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        int length = in.nextInt();

        System.out.print(name + " runs for");

        boolean flag = false;
        int hr, min;
        hr = length / 60;
        min = length % 60;

        if (hr == 1) {
            System.out.print(" " + hr + " hour");
            flag = true;
        } else if (hr > 1) {
            System.out.print(" " + hr + " hours");
            flag = true;
        }

        if (min == 1) {
            if (flag) {
                System.out.print(" and");
            }
            System.out.print(" " + min + " minute");
        } else if (min > 1) {
            if (flag) {
                System.out.print(" and");
            }
            System.out.print(" " + min + " minutes");
        }
        System.out.println();
    }
}
