package comp1110.homework.J06;

import java.util.Scanner;

public class Ones {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int mod = 0;
        int cnt = 0;

        while (num > 0) {
            mod = num % 2;
            num /= 2;
            if (mod == 1) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
