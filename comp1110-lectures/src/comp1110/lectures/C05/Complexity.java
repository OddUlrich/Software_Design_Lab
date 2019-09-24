package comp1110.lectures.C05;

import comp1110.lectures.A04.HashSet;
import comp1110.lectures.A04.Set;
import comp1110.lectures.A05.BSTSet;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Complexity extends Application {

    private static final int REPEATS = 500;

    /**
     * Read in words from dictionary
     *
     * @return A list of words
     */
    List<String> getWords() {
        ArrayList<String> words = new ArrayList<>();
        final String file = "resources/words/dictionary.txt";
        try {
            Scanner s = new Scanner(new File(file));
            while (s.hasNext()) {
                String word = s.next();
                words.add(word);
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Could not find file " + file);
        }

        System.out.println("Read in " + words.size() + " words");

        return words;
    }

    /**
     * Shuffle a list of Strings
     *
     * @param sorted The input list, which may be sorted
     * @return A shuffled list
     */
    List<String> shuffle(List<String> sorted) {
        Vector<String> in = new Vector<>(sorted);
        ArrayList<String> out = new ArrayList<>(sorted.size());
        Random rand = new Random();
        for (int i = 0; i < sorted.size(); i++) {
            int index = rand.nextInt(in.size());
            out.add(in.get(index));
            in.remove(index);
        }
        return out;
    }


    /**
     * Test the creation of a Set
     *
     * @param set   An instance of the set type to be used
     * @param words A set of words to be added to the Set
     * @param plot  The plot on which to plot the behavior of the algorithm
     * @param color The color to use for this plot
     */
    private void populateTest(Set<String> set, List<String> words, XYPlot plot, Color color) {
        int size = 1;
        Color faintColor = color.deriveColor(1, 1, 1, 0.25);
        while (size <= words.size()) {
            long nanos = 0;
            for (int j = 0; j < REPEATS; j++) {
                Set<String> s = set.newInstance();

                long start = System.nanoTime();
                for (int i = 0; i < size; i++) {
                    s.add(words.get(i));
                }
                long time = System.nanoTime() - start;
                nanos += time;
//			  plot.addPoint(size, time, faintColor);
            }

            double time = nanos / REPEATS;
            System.out.println("Populate: size " + size + ", time: " + time);
            plot.addPoint(size, time, color);
            size = size * 2;
        }
    }


    /**
     * Test the contains method for a Set
     *
     * @param set   An instance of the set type to be used
     * @param words A set of words to be added to be queried from within the Set
     * @param plot  The plot on which to plot the behavior of the algorithm
     * @param color The color to use for this plot
     */
    private void containsTest(Set<String> set, List<String> words, XYPlot plot, Color color) {
        int size = 1;
        Color faintColor = color.deriveColor(1, 1, 1, 0.25);
        while (size * 2 < words.size()) {
            size = size * 2;
        }
        while (size > 0) {
            /* create the set */
            Set<String> s = set.newInstance();
            for (int i = 0; i < size; i++) {
                s.add(words.get(i));
            }

            /* perform contains test */
            Random rand = new Random();
            int contained = 0;
            long nanos = 0;
            for (int j = 0; j < REPEATS; j++) {
                long start = System.nanoTime();
                for (int i = 1; i < size; i++) {
                    if (s.contains(words.get(rand.nextInt(size))))
                        contained++;
                }
                long time = System.nanoTime() - start;
                nanos += time;
                //     plot.addPoint(size, time/size, faintColor);
            }
            double meantime = (nanos / REPEATS) / size;
            System.out.println("Contains: size " + size + ", mean time: " + meantime);
            plot.addPoint(size, meantime, color);
            size = size / 2;
        }
    }

    @Override
    public void start(Stage arg0) throws Exception {
        List<String> words;
        BSTSet<String> bst;
        HashSet<String> hset;
        words = getWords().subList(0, 2048);

        /* populate */
        XYPlot populate = new XYPlot("Populate");
        populate.show();

        /* contains */
        XYPlot contains = new XYPlot("Contains");
        contains.show();

        bst = new BSTSet<>();
        populateTest(bst, words, populate, Color.CYAN);
        containsTest(bst, words, contains, Color.CYAN);
        /*
         * Shuffle the words before adding, so the binary tree doesn't end up
         * completely unbalanced (effectively degrading to a linked list.
         */
        Collections.shuffle(words);
        populateTest(bst, words, populate, Color.BLUE);
        containsTest(bst, words, contains, Color.BLUE);

        // hash set with one bucket is effectively a linked list
        hset = new HashSet<>(1);
        populateTest(hset, words, populate, Color.GOLD);
        // with more buckets, the cost of put and contains approaches constant time
        hset = new HashSet<>(40);
        populateTest(hset, words, populate, Color.ORANGE);
        hset = new HashSet<>(4000);
        populateTest(hset, words, populate, Color.RED);

        hset = new HashSet<>(1);
        containsTest(hset, words, contains, Color.GOLD);
        hset = new HashSet<>(40);
        containsTest(hset, words, contains, Color.ORANGE);
        hset = new HashSet<>(4000);
        containsTest(hset, words, contains, Color.RED);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
