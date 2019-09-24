package comp1110.lectures.J11;

import java.util.Random;

/**
 * Generates a string representing a randomly-generated Boggle dice roll.
 */
public class Boggle {
    static String[] dice = {"AJBBOO", "AFFPSK", "ANEAGE", "APSHCO", "QNUMHI", "ZNHRLN", "TDSTYI", "TTWOOA", "TLRYET", "TUMIOC", "EDVLRY", "EDRLXI", "EEGNHW", "EIOTSS", "ERHTWV", "EENUSI"};

    static final int ROW_LENGTH = 4;

    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        Random rand = new Random();
        boolean[] alreadyUsed = new boolean[dice.length];
        for (int i = 0; i < 16; i++) {
            int dieNumber = rand.nextInt(16);
            while (alreadyUsed[dieNumber]) {
                dieNumber = rand.nextInt(16); // keep trying random numbers
                //dieNumber = (dieNumber + 1) % dice.length; // check the next die
            }

            int face = rand.nextInt(6);
            char faceChar = dice[dieNumber].charAt(face);
            result.append(faceChar);
            if (i % ROW_LENGTH == ROW_LENGTH-1) result.append('\n');
            alreadyUsed[dieNumber] = true;
        }

        System.out.println(result.toString());
    }
}
