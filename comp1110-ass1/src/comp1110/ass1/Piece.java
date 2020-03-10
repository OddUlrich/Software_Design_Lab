package comp1110.ass1;

import java.util.Arrays;

/**
 * An enumeration representing the seven pieces in the IQStars game.
 * <p>
 * You may want to look at the 'Planet' example in the Oracle enum tutorial for
 * an example of an enumeration.
 * <p>
 * http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 */
public enum Piece {
    A(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(2, 0)}), // purple
    B(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(1, 1)}), // orange
    C(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(1, 1), new Hex(1, 2)}), // pink
    D(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(0, 1), new Hex(1, 1)}), // red
    E(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(1, 1), new Hex(2, 2)}), // green
    F(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(2, 0), new Hex(0, 1)}), // yellow
    G(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(0, 1), new Hex(1, 2)}); // blue

    public static final double MAX_PIECE_WIDTH = 3;
    public static final char INVALID_SPACE = '!';

    /**
     * A list of spaces covered by this piece in its default rotation.
     * Each space in the list is given as an offset from the origin (0,0).
     */
    public final Hex[] shape;

    Piece(Hex[] shape) {
        this.shape = shape;
    }

    public char getId() {
        return this.name().charAt(0);
    }

    /**
     * Return indices corresponding to which board spaces would be covered
     * by this piece, given a correct provided placement.
     * If a part of the piece would lie off the board in a given orientation,
     * return '!' for that part of the piece.
     * The order of the indices in the array does not matter.
     * <p>
     * Examples:
     * Given the piece placement string 'AAB' would return the indices: {'A', 'H', 'O'}.
     * Given the piece placement string 'CPD' would return the indices: {'B', 'H', 'O', 'P'}.
     * Given the piece placement string 'DAB' would return the indices: {'!', 'A', 'H', 'N'}.
     *
     * @param hex         the index of the hex in which the origin of the piece is placed ('A' to 'Z')
     * @param orientation which orientation the tile is in ('A' to 'F')
     * @return A set of indices corresponding to the board positions that would be covered by this piece
     */
    char[] getCovered(char hex, char orientation) {
        char[] cIndices = new char[this.shape.length];
        Hex[] hexArr = Arrays.copyOf(this.shape, this.shape.length);  // Create a copy of hex for operation.
        Hex vec = new Hex(hex);  // Bias for each hex.

        for (int iHex = 0; iHex < hexArr.length; iHex++) {
            for (int iRotate = 0; iRotate < orientation - 'A'; iRotate++) {
                hexArr[iHex] = hexArr[iHex].rotate60Degrees();
            }
            hexArr[iHex] = hexArr[iHex].translate(vec);
            cIndices[iHex] = hexArr[iHex].getIndex();
        }

        return cIndices;
    }
}
