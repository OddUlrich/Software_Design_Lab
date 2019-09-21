package comp1110.exam;


import java.util.ArrayList;

/**
 * COMP1110 Final Exam, Question 1i
 */
public class Q1Even {
    /**
     * This function takes a positive integer, n,  and returns an array
     * of ints containing all even integers between 1 and n, inclusive of n.
     *
     * for example:
     *    n = 5
     * the result will be
     *        {2, 4}
     */
    public static int[] even(int n) {
        if (n <= 0) {
            return null;
        } else {
            int[] arr = new int[n/2];
            int idx = 0;

            for (int i = 2; i <= n; i++) {
                if (i % 2 == 0) {
                    arr[idx] = i;
                    idx++;
                }
            }

            return arr;
        }
    }
}
