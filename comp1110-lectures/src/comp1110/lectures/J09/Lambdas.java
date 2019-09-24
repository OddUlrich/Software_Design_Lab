package comp1110.lectures.J09;

import java.util.function.IntPredicate;

public class Lambdas {
    static void printMultiplesOf(int divisor, int[] a) {
        System.out.println("Printing multiples of " + divisor);
        for (int i = 0; i < a.length; i++) {
            if (a[i] % divisor == 0) {
                System.out.print(a[i] + " ");
            }
        }
        System.out.println();
    }

    static void printMatching(int[] a, IntPredicate predicate) {
        for (int i = 0; i < a.length; i++) {
            if (predicate.test(a[i])) {
                System.out.print(a[i] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[42];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        //printMultiplesOf(2, a);
        //printMultiplesOf(3, a);
        System.out.println("Even numbers");
        printMatching(a, x -> x % 2 == 0);
        System.out.println();

        System.out.println("Multiples of three");
        printMatching(a, x -> x % 3 == 0);
        System.out.println();

        System.out.println("Squares");
        printMatching(a, x -> {
            double sqrtX = Math.sqrt((double)x);
            return Math.round(sqrtX) == sqrtX;
        }
        );


    }

}
