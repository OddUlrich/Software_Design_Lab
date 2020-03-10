package comp1110.ass1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;
import java.util.HashSet;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class PieceFixedTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(300);

    @Test
    public void onePiece() {
        assertTrue("piece E is part of the objective, but isPieceFixed returned false", new IQStars("EKA").isPieceFixed('E'));
        assertFalse("piece F is not part of the objective, but isPieceFixed returned false", new IQStars("EKA").isPieceFixed('F'));
        assertTrue("piece G is part of the objective, but isPieceFixed returned false", new IQStars("GAA").isPieceFixed('G'));
        assertTrue("piece A is part of the objective, but isPieceFixed returned false", new IQStars("ADB").isPieceFixed('A'));
        assertTrue("piece B is part of the objective, but isPieceFixed returned false", new IQStars("BKC").isPieceFixed('B'));
    }

    @Test
    public void sampleTest() {
        for (int i = 0; i < IQStars.SAMPLE_OBJECTIVES.length; i++) {
            for (int j = 0; j < IQStars.SAMPLE_OBJECTIVES[i].length; j++) {
                String objective = IQStars.SAMPLE_OBJECTIVES[i][j];
                IQStars puzzle = new IQStars(objective);
                HashSet<Piece> free = new HashSet<>(Arrays.asList(Piece.values()));
                for (int k = 0; k < objective.length(); k += 3) {
                    assertTrue("piece " + objective.charAt(k) + " is part of the objective, but isPieceFixed returned false", puzzle.isPieceFixed(objective.charAt(k)));
                    free.remove(Piece.valueOf(String.valueOf(objective.charAt(k))));
                }
                for (Piece freePiece : free) {
                    assertFalse("piece " + freePiece + " is not part of the objective, but isPieceFixed returned true", puzzle.isPieceFixed(freePiece.getId()));
                }
            }
        }
    }
}
