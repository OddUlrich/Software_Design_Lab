package comp1110.homework.J06;

import java.util.Scanner;

public class Prime {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        boolean flag;

        for (int n = 2; n <= num; n++) {
            flag = true;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0)
                {
                    flag = false;
                    break;    // n is not prime, go to the next number.
                }
            }
            if (flag) {
                System.out.println(n);
            }
        }
    }
}
