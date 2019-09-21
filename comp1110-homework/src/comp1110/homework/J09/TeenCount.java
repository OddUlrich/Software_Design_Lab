package comp1110.homework.J09;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class TeenCount {
    public static int[] teenLess(int[] array) {
        boolean bIsTeen;
        int[] result = new int[0];
        for (int num : array) {
            bIsTeen = applyPredicate(num, x -> (x >= 13 && x <= 19));
            if (!bIsTeen) {
                // Create a new array with longer length.
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = num;
            }
        }
        return result;
    }

    private static boolean applyPredicate(int value, IntPredicate predicate) {
        return predicate.test(value);
    }

}
