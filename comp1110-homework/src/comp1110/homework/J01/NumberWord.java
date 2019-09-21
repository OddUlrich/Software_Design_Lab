package comp1110.homework.J01;

import java.util.Scanner;

public class NumberWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //System.out.println("Enter a string: ");
        String str = in.nextLine();
        //System.out.println("Enter a integer: ");
        int num = in.nextInt();
        System.out.println(num + ", " + str);
    }
}
