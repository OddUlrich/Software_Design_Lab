package comp1110.homework.J06;

import java.util.Scanner;

public class Even {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        for (int i = 2; i <= n; i += 2) {
            System.out.println(i);
        }
    }
}
