package comp1110.ass1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ValidPiecePlacementTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(300);

    @Test
    public void valid() {
        assertTrue("Piece placement AAA is valid, but IQStars.isValidPiecePlacement returned false", IQStars.isValidPiecePlacement("AAA"));
        assertTrue("Piece placement BMB is valid, but IQStars.isValidPiecePlacement returned false", IQStars.isValidPiecePlacement("BMB"));
        assertTrue("Piece placement GWF is valid, but IQStars.isValidPiecePlacement returned false", IQStars.isValidPiecePlacement("GWF"));
    }

    @Test
    public void invalidPiece() {
        assertFalse("Piece placement HAL is invalid, but IQStars.isValidPiecePlacement returned true", IQStars.isValidPiecePlacement("HAL"));
        assertFalse("Piece placement aAA is invalid, but IQStars.isValidPiecePlacement returned true", IQStars.isValidPiecePlacement("aAA"));
        assertTrue("Piece placement AAA is valid, but IQStars.isValidPiecePlacement returned false", IQStars.isValidPiecePlacement("AAA"));
    }

    @Test
    public void invalidHex() {
        assertFalse("Piece placement A0A is invalid, but IQStars.isValidPiecePlacement returned true", IQStars.isValidPiecePlacement("A0A"));
        assertFalse("Piece placement AaA is invalid, but IQStars.isValidPiecePlacement returned true", IQStars.isValidPiecePlacement("AaA"));
        assertFalse("Piece placement AzA is invalid, but IQStars.isValidPiecePlacement returned true", IQStars.isValidPiecePlacement("AzA"));
        assertTrue("Piece placement AAA is valid, but IQStars.isValidPiecePlacement returned false", IQStars.isValidPiecePlacement("AAA"));
    }

    @Test
    public void invalidRotation() {
        assertFalse("Piece placement AA0 is invalid, but IQStars.isValidPiecePlacement returned true", IQStars.isValidPiecePlacement("AA0"));
        assertFalse("Piece placement AAG is invalid, but IQStars.isValidPiecePlacement returned true", IQStars.isValidPiecePlacement("AAG"));
        assertTrue("Piece placement AAA is valid, but IQStars.isValidPiecePlacement returned false", IQStars.isValidPiecePlacement("AAA"));
    }


}
