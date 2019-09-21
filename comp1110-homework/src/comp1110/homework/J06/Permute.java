package comp1110.homework.J06;

import java.util.Arrays;
import java.util.Scanner;

public class Permute {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();

        if (s1.length() != s2.length()) {
            System.out.println("No");
            return;
        }

        char[] c1 = s1.toCharArray();
        char[] c2= s2.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);
        boolean isSame = Arrays.equals(c1, c2);

        if (isSame) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
