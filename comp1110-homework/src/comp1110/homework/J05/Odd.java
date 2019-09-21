package comp1110.homework.J05;

import java.util.Scanner;

public class Odd {
    public static boolean isOdd(int i) {
        int mod = i % 2;
        if (mod != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        boolean flag = Odd.isOdd(num);

        if (flag) {
            System.out.println(num + " is odd");
        } else {
            System.out.println(num + " is even");
        }
    }
}
