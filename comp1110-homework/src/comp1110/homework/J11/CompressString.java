package comp1110.homework.J11;

import java.util.Scanner;

public class CompressString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        StringBuilder compress = new StringBuilder();

        input = in.nextLine();
        if (input.length() <= 0) {
            System.out.println();
            return;
        }

        int cnt = 1;
        compress.append(input.charAt(0));

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                cnt++;
            } else {
                if (cnt > 1) {
                    compress.append(cnt);
                    cnt = 1;
                }
                // Append new character.
                compress.append(input.charAt(i));
            }
        }
        if (cnt > 1) {
            compress.append(cnt);  // Append the last count into string.
        }

        System.out.println(compress.toString());
    }
}
