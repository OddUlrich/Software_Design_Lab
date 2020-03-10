package comp1110.ass2;

public class Board {
    static final int BOARD_ROWS_NUM = 7;
    static final int BOARD_COLS_NUM = 7;
    private static final int EXIT_NUM = 12;

    private Tile[][] board;
    private Exit[] exits;
    private String curPlacement;
    private boolean isUsedSpeInRound;
    private int usedSpeTile;  // The number of used special tiles.
    private int roundNum;

    // Properties for all exits.
    private static final int[][] HIGHWAY_EXITS = {
            {1, 0, Exit.UP}, {5, 0, Exit.UP},
            {0, 3, Exit.LEFT}, {7, 3, Exit.RIGHT},
            {1, 7, Exit.DOWN}, {5, 7, Exit.DOWN}
    };
    private static final int[][] RAILWAY_EXITS = {
            {3, 0, Exit.UP},
            {0, 1, Exit.LEFT}, {7, 1, Exit.RIGHT},
            {0, 5, Exit.LEFT}, {7, 5, Exit.RIGHT},
            {3, 7, Exit.DOWN}
    };

    public Board() {
        board = new Tile[BOARD_ROWS_NUM][BOARD_COLS_NUM];
        exits = new Exit[EXIT_NUM];
        curPlacement = "";
        roundNum = 1;  // Initialize the game in the first round.

        // Create all exits objects.
        int i = 0;
        for (; i < HIGHWAY_EXITS.length; i++) {
            int[] exit = HIGHWAY_EXITS[i];
            exits[i] = new Exit(exit[0], exit[1], exit[2]);
        }
        int j = 0;
        for (; j < RAILWAY_EXITS.length; j++) {
            int[] exit = RAILWAY_EXITS[j];
            exits[i + j] = new Exit(exit[0], exit[1], exit[2]);
        }
    }

    /**
     * A legal placement is conducted to save a tile object into the board matrix.
     * Before calling this method,
     *
     * @param tile the tile object that will be saved into the board matrix.
     * @author Wyman
     */
    public void placeTileOnBoard(Tile tile) {
        String placeStr = tile.getPlacementStr();

        // Check the validation of tile's placement string.
        if (!RailroadInk.isTilePlacementWellFormed(placeStr)) {
            System.err.println("Tile placement with incomplete string: " + placeStr);
            return;
        }

        this.curPlacement = this.curPlacement + placeStr;
        board[tile.getRowPos()][tile.getColPos()] = tile;
        if (tile.name.charAt(0) == 'S') {
            isUsedSpeInRound = true;
            this.usedSpeTile++;
        }
    }

    /**
     * Relevant operation with the status of game rounds.
     *
     * @author Wyman
     */
    public void nextRound() {
        this.roundNum++;
    }
    public int getThisRound() {
        return this.roundNum;
    }

    /**
     * Relevant operation with the status of special tiles using.
     *
     * @author Wyman
     */
    public int getUsedSpeTileNum() {
        return this.usedSpeTile;
    }
    public boolean isSpeTileUsedInRound() {
        return this.isUsedSpeInRound;
    }
    public void resetSpeUsedFlag() {
        this.isUsedSpeInRound = false;
    }

    /**
     * Use the position string to get the tile instance in the 2-dimensional array of the tile board.
     *
     * @param row row number in the board.
     * @param col column number in the board.
     *
     * @author Wyman
     * @return Tile object that in the specific position in the board.
     */
    Tile getTileObjByPosition(int row, int col) {
        return board[row][col];
    }

    /**
     * Return the array of exits in the board.
     *
     * @author Wyman
     * @return exits
     */
    Exit[] getExitsArray() {
        return exits;
    }

    /**
     * Use the position string to get the exit instance in the array.
     *
     * @param row row number of the exit position
     * @param col column number of the exit position
     * @return exit
     */
    Exit getExitWithPos(int row, int col) {
        for (Exit exit: exits) {
            if ((exit.getRowPos() == row) && (exit.getColPos() == col)) {
                return exit;
            }
        }
        return null;
    }
}