package comp1110.homework.J14;

import java.util.ArrayList;
import java.util.Scanner;

public class Names {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> str = new ArrayList<>();

        while (in.hasNext()) {
            str.add(in.nextLine());
        }

        for (int i = str.size() - 1; i >= 0; i--) {
            System.out.println(str.get(i));
        }
    }
}
