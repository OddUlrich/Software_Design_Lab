package comp1110.homework.J07;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    public static int findRange(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        Arrays.sort(array);
        return array[array.length - 1] - array[0];
    }

    public static int getMax(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        Arrays.sort(array);
        return array[array.length - 1];
    }

    public static int getSecondHighest(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        Arrays.sort(array);
        return array[array.length - 2];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size, opt;

        // Read the size of numbers array.
        opt = in.nextInt();
        size = in.nextInt();
        int[] arrNum = new int[size];
        for (int i = 0; i < size; i++) {
            arrNum[i] = in.nextInt();
        }

        // Print out the menu.
        System.out.println("Please enter:");
        System.out.println("1 to find range of the scores");
        System.out.println("2 to find the maximum score");
        System.out.println("3 to find the second highest score");

        // Read the input option and execute.
        int result;
        switch (opt) {
            case 1:
                result = Menu.findRange(arrNum);
                System.out.println(result);
                break;
            case 2:
                result = Menu.getMax(arrNum);
                System.out.println(result);
                break;
            case 3:
                result = Menu.getSecondHighest(arrNum);
                System.out.println(result);
                break;
            default:
                System.out.println("Invalid Option Selected");
        }
    }
}
