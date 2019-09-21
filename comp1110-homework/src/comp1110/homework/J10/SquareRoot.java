package comp1110.homework.J10;

import java.util.ArrayList;
import java.util.List;

public class SquareRoot {
    public static String findPreciseRoot(int n, int d) {
        double root;

        StringBuilder str = new StringBuilder();
        String output;

        if (n < 0 || d < 0) {
            return "-1";
        }

        root = Math.sqrt((double) n);

        if (d == 0) {
            str.append((int) root);
        } else {
            if (root - Math.round(root) == 0) {
                str.append((int) root);
            } else {
                long tmpLong = Math.round(root * Math.pow(10, d));
                String tmpStr = Long.toString(tmpLong);
                str.append(tmpStr.substring(0, tmpStr.length() - d)
                            + "."
                            + tmpStr.substring(tmpStr.length() - d));
            }
        }

        output = str.toString();
        return output;
    }

    public static void main(String[] args) {
        System.out.println(findPreciseRoot(19, 4));
        System.out.println(findPreciseRoot(25, 2));
        System.out.println(findPreciseRoot(-10, 0));
    }
}
