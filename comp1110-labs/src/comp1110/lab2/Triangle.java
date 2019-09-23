package comp1110.lab2;

import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int height = in.nextInt();
        int cnt;

        for (int row = 1; row < height; row++) {
            cnt = 0;
            while (cnt < height - row) {
                System.out.print(" ");
                cnt++;
            }
            System.out.print("*");

            if (row == 1) {
                System.out.println();
            }
            else {
                cnt = 0;
                while (cnt < (row - 1) * 2 - 1) {
                    System.out.print(" ");
                    cnt++;
                }
                System.out.println("*");
            }
        }

        cnt = 0;
        while (cnt < height * 2 - 1 ) {
            System.out.print("*");
            cnt++;
        }
        System.out.println();
    }
}
