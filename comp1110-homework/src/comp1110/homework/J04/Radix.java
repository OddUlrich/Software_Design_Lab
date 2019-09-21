package comp1110.homework.J04;

import java.util.Scanner;

public class
Radix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int srcRdx = in.nextInt();
        int desRdx = in.nextInt();
        String str = in.next();

        String resStr;
        resStr = Integer.toString(Integer.parseInt(str, srcRdx), desRdx);
        System.out.println(resStr);
    }
}
