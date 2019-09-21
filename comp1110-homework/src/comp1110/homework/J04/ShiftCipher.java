package comp1110.homework.J04;

import java.util.Scanner;

public class ShiftCipher {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inStr;
        int shift;
        char tmp;

        inStr = in.nextLine();
        shift = in.nextInt();

        char[] arrStr = inStr.toCharArray();
        for (int i = 0; i < arrStr.length; i++) {
            if (Character.isLetter(arrStr[i])) {
                tmp = (char)(arrStr[i] + shift);
                if (Character.isUpperCase(arrStr[i]) && !Character.isUpperCase(tmp)){
                    arrStr[i] += (shift - 26);
                } else if (Character.isLowerCase(arrStr[i]) && !Character.isLowerCase(tmp)) {
                    arrStr[i] += (shift - 26);
                } else {
                    arrStr[i] = tmp;
                }
            }
        }

        System.out.println(String.copyValueOf(arrStr));
    }
}
