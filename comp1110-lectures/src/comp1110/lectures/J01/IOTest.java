package comp1110.lectures.J01;

import java.util.Scanner;

public class IOTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter an integer a:");
        int a = in.nextInt();
        System.out.println("Enter a string b:");
        String b = in.next();
        System.out.println("a: " + a + " b: " + b);
    }
}
