package comp1110.ass2;

public class Exit {

    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    public int xPos;
    public int yPos;
    public int orientation;  // UP is default.

    private int colPos;
    private int rowPos;

    int graphId; // Default is 0; it will be greater than 0 if assigned to a graph.

    public Exit(int xPos, int yPos, int orientation) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.orientation = orientation;

        fixPosition();
    }

    /**
     * This method aim at getting the logic position for checking the connection relationship between exit and tile.
     * As the two layers of exit are both 8*8 size with a position offset to fix on the edge of the board.
     * The row position and column position are only used to make the viewer.
     *
     * The exit at the bottom or the right edge will be fixed to the position that is the same as the tile it connected to:
     *     (yPos, 7) --> (yPos, 6)
     *     (7, xPos) --> (6, xPos)
     *
     * @author Wyman
     */
    private void fixPosition() {
        if (this.xPos == 7) {
            colPos = xPos - 1;
            rowPos = yPos;
        }
        else if (this.yPos == 7) {
            colPos = xPos;
            rowPos = yPos - 1;
        }
        else{
            colPos = xPos;
            rowPos = yPos;
        }
    }

    /**
     * Get the row number for the current exit.
     *
     * @author Wyman
     * @return the row number for this exit in the tile board.
     */
    public int getRowPos() {
        return rowPos;
    }

    /**
     * Get the column number for the current exit.
     * The number will be from 0 to 6.
     *
     * @author Wyman
     * @return the column number for this exit in the tile board.
     */
    public int getColPos() {
        return colPos;
    }
}
