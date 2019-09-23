package comp1110.lab4;

import java.util.Random;

public class Dice {
    public static final int RANDOM_OFFSET = 10;
    public static void main(String[] args) {
        Random rand = new Random();

        for (int num = 0; num < 10; num++) {
            System.out.println(rand.nextInt(6) + RANDOM_OFFSET);
        }
    }
}
