package comp1110.lectures.C01;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.Assert.assertTrue;

public class Boggle {
    static String[] dice = {"AJBBOO", "AFFPSK", "ANEAGE", "APSHCO", "QNUMHI", "ZNHRLN", "TDSTYI", "TTWOOA", "TLRYET", "TUMIOC", "EDVLRY", "EDRLXI", "EEGNHW", "EIOTSS", "ERHTWV", "EENUSI"};

    static final int ROW_LENGTH = 4;

    /**
     * The set of neighbours for each die
     */
    static List<HashSet<Integer>> neighbours = new ArrayList<HashSet<Integer>>(dice.length);

    /**
     * The set of all words in the dictionary.
     */
    private static Set<String> dictionary;

    /**
     * The set of all prefixes of all words in the dictionary.
     */
    private static Set<String> prefixes;

    /**
     * This code generates the set of neighbours for each die.
     * This will be used in the search to know which dice can be visited
     * from a given die.
     *
     * We assume that the dice are numbered:
     *
     *  0   1  2  3
     *  4   5  6  7
     *  8   9 10 11
     *  12 13 14 15
     *
     *  So for example the neighbours of die 0 are 1, 4, and 5;
     *  the neighbours of die 5 are 0, 1, 2, 4, 6, 8, 9, and 10; and
     *  the neighbours of die 11 are 6, 7, 10, 14, and 15.
     */
    static {
        /*
         * This array describes the difference between the index of a die and
         * the index of each of its possible neighbours.
         */
        int[] distance = {-1, -ROW_LENGTH - 1, -ROW_LENGTH, -ROW_LENGTH + 1, +1, +ROW_LENGTH + 1, +ROW_LENGTH, +ROW_LENGTH - 1};
        for (int i = 0; i < dice.length; i++) {
            HashSet<Integer> myNeighbours = new HashSet<>();
            /*
             * For each distance, determine whether the resulting die is
             * really a neighbour, and if so, add it
             */
            for (int d : distance) {
                int neighbourId = i + d;
                int ncol = neighbourId % ROW_LENGTH; // neighbour column
                int icol = i % ROW_LENGTH; // my column
                if (neighbourId >= 0 && neighbourId <= 15 && Math.abs(ncol - icol) <= 1) {
                    myNeighbours.add(neighbourId);
                }
            }
            neighbours.add(myNeighbours);
        }

        dictionary = processDictionary("assets/dictionary.txt");
    }

    /**
     * The list of characters in this Boggle game, which is the list of the
     * upward-facing sides of each of the 16 dice.
     */
    private char[] boggleCharacters;

    /**
     * Create a new Boggle game with a list of randomly shaken dice.
     */
    public Boggle() {
        boggleCharacters = shake();
    }

    /**
     * Solve this boggle game.
     *
     * @return a set of all the words in the dictionary that can be made
     * using this game's boggleCharacters
     */
    public Set<String> solve() {
        Set<String> results = new HashSet<>();

        boolean[] used = new boolean[boggleCharacters.length];

        for (int i = 0; i < boggleCharacters.length; i++) {
            findWords(results, i, "", used); //?
        }

        return results;
    }

    /**
     * Recursive function for finding Boggle words.
     * <p>
     * This function starts at a particular die, given a prefix which is the
     * list of letters encountered so far in the current search.
     * <p>
     * If the concatenation of the prefix and the current die leads to a valid
     * word, then the word is added to the results.
     * To continue the search, each of the die's neighbours are visited if they
     * have not already been used in the current search.
     * <p>
     * An optimization is that we don't continue search if the concatenation of
     * the prefix and the current die is not a viable prefix i.e. it does not
     * appear in the set of prefixes of all words in the dictionary.
     *
     * @param results the set of valid words found so far
     * @param start   the index of the die this search is to start from
     * @param prefix  the string of characters seen so far in this search
     * @param used    an array of boolean indicating whether each character in
     *                boggleCharacters has already been used in this search
     */
    private void findWords(Set<String> results, int start, String prefix, boolean[] used) {
        used[start] = true;

        String candidate = prefix + boggleCharacters[start];
        if (candidate.length() >= 3 && dictionary.contains(candidate)) {
            results.add(candidate);
        }

        if (prefixes.contains(candidate)) {
            for (int n : neighbours.get(start)) {
                if (!used[n]) {
                    findWords(results, n, candidate, used);
                }
            }
        }

        used[start] = false;
    }

    @Test
    public void testEnglishWords() {
        Boggle boggle = new Boggle();
        Set<String> words = boggle.solve();
        for (String word : words) {
            assertTrue(dictionary.contains(word));
        }
    }

    @Test
    public void testWordLength() {
        Boggle boggle = new Boggle();
        Set<String> words = boggle.solve();
        for (String word : words) {
            assertTrue("Word is too short: " + word, word.length() >= 3);
            assertTrue("Word is too long: " + word, word.length() <= 16);
        }
    }

    /**
     * 0   1  2  3
     * 4   5  6  7
     * 8   9 10 11
     * 12 13 14 15
     */
    @Test
    public void testNeighbours() {
        int[] neighboursOfZero = {1, 4, 5};
        HashSet<Integer> computedZero = neighbours.get(0);
        assertTrue(computedZero.size() == 3);
        for (Integer n : neighboursOfZero) {
            assertTrue(computedZero.contains(n));
        }
    }

    /**
     * Read in a dictionary from a file name, and create a set of words that
     * will be used by the Boggle solver to decide if a string is a valid word.
     * <p>
     * The simple approach is just to read all the words in a dictionary into
     * a set of Strings.
     * <p>
     * TODO optimizations
     *
     * @param filename the name of the file containing the dictionary
     * @return a set of words to be used by the Boggle solver
     */
    private static Set<String> processDictionary(String filename) {
        Set<String> words = new HashSet<>();
        prefixes = new HashSet<>();
        try {
            Scanner s = new Scanner(new File(filename));
            while (s.hasNext()) {
                String word = s.next().toUpperCase();
                if (word.length() >= 3) {
                    words.add(word);
                    for (int prefixLength = 1; prefixLength < word.length(); prefixLength++) {
                        prefixes.add(word.substring(0, prefixLength));
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Found " + words.size() + " words.");
        return words;
    }

    /**
     * Create a Boggle board by 'shaking' the 16 dice.
     * This function randomizes the order of the dice, and also rolls each die,
     * choosing an upward face ranomly.
     *
     * @return a randomized array of 16 characters,
     * each chosen from one of the boggle dice
     */
    private static char[] shake() {
        char[] boggleDice = new char[16];
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
            boggleDice[i] = faceChar;
            System.out.print(faceChar);
            if (i % ROW_LENGTH == ROW_LENGTH - 1) System.out.print('\n');
            alreadyUsed[dieNumber] = true;
        }
        return boggleDice;
    }

    public static void main(String[] args) {
        Boggle boggle = new Boggle();
        Set<String> words = boggle.solve();
        System.out.println("Found " + words.size() + " Boggle words.");
        for (String w : words) {
            System.out.println(w);
        }
    }
}
