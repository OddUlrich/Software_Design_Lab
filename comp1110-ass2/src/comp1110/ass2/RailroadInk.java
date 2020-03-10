package comp1110.ass2;

import java.util.Random;

public class RailroadInk {
    /**
     * Determine whether a tile placement string is well-formed:
     * - it consists of exactly 5 characters;
     * - the first character represents a die A or B, or a special tile S
     * - the second character indicates which tile or face of the die (0-5 for die A and special tiles, or 0-2 for die B)
     * - the third character represents the placement row A-G
     * - the fourth character represents the placement column 0-6
     * - the fifth character represents the orientation 0-7
     *
     * @author Senyuan
     * @param tilePlacementString a candidate tile placement string
     * @return true if the tile placement is well formed
     */
    public static boolean isTilePlacementWellFormed(String tilePlacementString) {
        // FIXME Task 2: determine whether a tile placement is well-formed

        int length;
        length = tilePlacementString.length();
        if (length != 5)
            return false; // Whether it contains 5 characters.

        boolean flag = true;

        if (tilePlacementString.charAt(0) == 'A' || tilePlacementString.charAt(0) == 'S'){
            flag &= (tilePlacementString.charAt(1) >= '0' && tilePlacementString.charAt(1) <= '5'); //validity of specific die of A or S: 0 - 5
        }
        else if (tilePlacementString.charAt(0) == 'B'){
            flag &= (tilePlacementString.charAt(1) >= '0' && tilePlacementString.charAt(1) <= '2'); //validity of specific die of B: 0 - 2
        }
        else
            return false;

        flag &= (tilePlacementString.charAt(2) >= 'A' && tilePlacementString.charAt(2) <= 'G'); //validity of the placement row: A - G

        flag &= (tilePlacementString.charAt(3) >= '0' && tilePlacementString.charAt(3) <= '6'); //validity of the placement column: 0 - 6

        flag &= (tilePlacementString.charAt(4) >= '0' && tilePlacementString.charAt(4) <= '7'); //validity of the orientation: 0 - 7

        return flag;
    }

    /**
     * Determine whether a board string is well-formed:
     * - it consists of exactly N five-character tile placements (where N = 1 .. 31);
     * - each piece placement is well-formed
     * - no more than three special tiles are included
     *
     * @param boardString a board string describing the placement of one or more pieces
     * @return true if the board string is well-formed
     */
    public static boolean isBoardStringWellFormed(String boardString) {
        // FIXME Task 3: determine whether a board string is well-formed

        if (boardString == null) {
            return false;
        }

        int boardStringLen = boardString.length();

        // It consists of exactly N five - character tile placements.
        if ((boardStringLen / 5 < 1) || (boardStringLen / 5 > 31) || (boardStringLen % 5 != 0)) {
            return false;
        }

        // Count special tiles.
        int specialCnt = 0;
        for (int i = 0; i < boardStringLen; i += 5) {
            String tilePlacementString = boardString.substring(i, i+5);
            if(tilePlacementString.charAt(0) == 'S')
                specialCnt++;
            // each piece placement is well-formed.
            if (!isTilePlacementWellFormed(tilePlacementString)) {
                return false;
            }
        }
        // No more than three special tiles are included.
        if (specialCnt > 3){
            return false;
        }

        return true;
    }

    /**
     * Determine whether the provided placements are neighbours connected by at least one validly connecting edge.
     * For example,
     * - areConnectedNeighbours("A3C10", "A3C23") would return true as these tiles are connected by a highway edge;
     * - areConnectedNeighbours("A3C23", "B1B20") would return false as these neighbouring tiles are disconnected;
     * - areConnectedNeighbours("A0B30", "A3B23") would return false as these neighbouring tiles have an
     * invalid connection between highway and railway; and
     * areConnectedNeighbours("A0B30", "A3C23") would return false as these tiles are not neighbours.
     *
     * @author Senyuan
     * @return true if the placements are connected neighbours
     */
    public static boolean areConnectedNeighbours(String tilePlacementStringA, String tilePlacementStringB) {
        // FIXME Task 5: determine whether neighbouring placements are connected
        boolean flag;

        //Whether neighbours
        flag = (tilePlacementStringA.charAt(2) == tilePlacementStringB.charAt(2) && (tilePlacementStringA.charAt(3) + 1 == tilePlacementStringB.charAt(3) || tilePlacementStringA.charAt(3) == tilePlacementStringB.charAt(3) + 1))
                || (tilePlacementStringA.charAt(3) == tilePlacementStringB.charAt(3) && (tilePlacementStringA.charAt(2) + 1 == tilePlacementStringB.charAt(2) || tilePlacementStringA.charAt(2) == tilePlacementStringB.charAt(2) + 1));

        if (!flag) {
            return false;
        }

        Tile tileA = new Tile(tilePlacementStringA);
        Tile tileB = new Tile(tilePlacementStringB);
        int[] edgeA = tileA.getCurrentEdgesStatus();
        int[] edgeB = tileB.getCurrentEdgesStatus();

        //A is on the right side of B
        if (tilePlacementStringA.charAt(2) == tilePlacementStringB.charAt(2)
                && tilePlacementStringA.charAt(3) == tilePlacementStringB.charAt(3) + 1)
            //A's left edge is equal to B's right edge but not blank
            flag &= edgeA[Tile.LEFT] == edgeB[Tile.RIGHT] && edgeA[Tile.LEFT] != Tile.BLANK;

        //A is on the left side of B
        else if (tilePlacementStringA.charAt(2) == tilePlacementStringB.charAt(2)
                && tilePlacementStringA.charAt(3) + 1 == tilePlacementStringB.charAt(3))
            //A's right edge is equal to B's left edge but not blank
            flag &= edgeA[Tile.RIGHT] == edgeB[Tile.LEFT] && edgeA[Tile.RIGHT] != Tile.BLANK;

        //A is on the down side of B
        else if (tilePlacementStringA.charAt(3) == tilePlacementStringB.charAt(3)
                && tilePlacementStringA.charAt(2) == tilePlacementStringB.charAt(2) + 1)
            //A's up edge is equal to B's down edge but not blank
            flag &= edgeA[Tile.UP] == edgeB[Tile.DOWN] && edgeA[Tile.UP] != Tile.BLANK;

        //A is on the up side of B
        else if (tilePlacementStringA.charAt(3) == tilePlacementStringB.charAt(3)
                && tilePlacementStringA.charAt(2) + 1 == tilePlacementStringB.charAt(2))
            //A's down edge is equal to B's up edge but not blank
            flag &= edgeA[Tile.DOWN] == edgeB[Tile.UP] && edgeA[Tile.DOWN] != Tile.BLANK;

        else
            flag = false;

        return flag;
    }

    /**
     * Determine whether the provided placements are neighbours illegally connected by at least one connecting edge.
     * Illegal connections mean a highway edge is connecte to a railway edge.
     *
     * For example,
     * - areConnectedNeighbours("A0B30", "A3B23") would return true as these neighbouring tiles have an invalid connection between highway and railway;
     * - areConnectedNeighbours("A3C10", "A3C23") would return false as these tiles are connected by a highway edge;
     * - areConnectedNeighbours("A3C23", "B1B20") would return false as these neighbouring tiles are disconnected;
     * - areConnectedNeighbours("A0B30", "A3C23") would return false as these tiles are not neighbours.
     *
     * @return true if the placements are illegally connected neighbours
     */
    public static boolean areIllegalConnectedNeighbours(Tile tileA, Tile tileB) {
        boolean flag = false;

        int[] edgeA = tileA.getCurrentEdgesStatus();
        int[] edgeB = tileB.getCurrentEdgesStatus();

        //A is on the right side of B
        if (tileA.pos.charAt(0) == tileB.pos.charAt(0) && tileA.pos.charAt(1) == tileB.pos.charAt(1) + 1) {
            flag |= (edgeA[Tile.LEFT] == Tile.RAIL) && (edgeB[Tile.RIGHT] == Tile.HIGH);
            flag |= (edgeA[Tile.LEFT] == Tile.HIGH) && (edgeB[Tile.RIGHT] == Tile.RAIL);
        }
        //A is on the left side of B
        else if (tileA.pos.charAt(0) == tileB.pos.charAt(0) && tileA.pos.charAt(1) + 1 == tileB.pos.charAt(1)) {
            flag |= (edgeA[Tile.RIGHT] == Tile.RAIL) && (edgeB[Tile.LEFT] == Tile.HIGH);
            flag |= (edgeA[Tile.RIGHT] == Tile.HIGH) && (edgeB[Tile.LEFT] == Tile.RAIL);
        }
        //A is on the down side of B
        else if (tileA.pos.charAt(1) == tileB.pos.charAt(1) && tileA.pos.charAt(0) == tileB.pos.charAt(0) + 1) {
            flag |= (edgeA[Tile.UP] == Tile.RAIL) && (edgeB[Tile.DOWN] == Tile.HIGH);
            flag |= (edgeA[Tile.UP] == Tile.HIGH) && (edgeB[Tile.DOWN] == Tile.RAIL);
        }
        //A is on the up side of B
        else if (tileA.pos.charAt(1) == tileB.pos.charAt(1) && tileA.pos.charAt(0) + 1 == tileB.pos.charAt(0)) {
            flag |= (edgeA[Tile.DOWN] == Tile.RAIL) && (edgeB[Tile.UP] == Tile.HIGH);
            flag |= (edgeA[Tile.DOWN] == Tile.HIGH) && (edgeB[Tile.UP] == Tile.RAIL);
        }
        else {
            flag = false;
        }

        return flag;
    }

    /**
     * Check tile' edges relationship with exits.
     * - A highway connect to a highway;
     * - A railway connect to a railway.
     *
     * @param tile, the tile instance.
     * @param exitDirection, (UP, RIGHT, DOWN, LEFT).
     * @return true if connected to the exit.
     */
    public static boolean isConnectedExit(Tile tile, int exitDirection) {
        boolean isConnected = false;
        int[] edgeStatus = tile.getCurrentEdgesStatus();
        int row = tile.getRowPos();
        int col = tile.getColPos();

        switch (exitDirection) {
            case Exit.UP:
                if (col == 1 || col == 5) {
                    isConnected = (edgeStatus[Tile.UP] == Tile.HIGH);
                } else if (col == 3) {
                    isConnected = (edgeStatus[Tile.UP] == Tile.RAIL);
                }
                break;
            case Exit.RIGHT:
                if (row == 1 || row == 5) {
                    isConnected = (edgeStatus[Tile.RIGHT] == Tile.RAIL);
                } else if (row == 3) {
                    isConnected = (edgeStatus[Tile.RIGHT] == Tile.HIGH);
                }
                break;
            case Exit.DOWN:
                if (col == 1 || col == 5) {
                    isConnected = (edgeStatus[Tile.DOWN] == Tile.HIGH);
                } else if (col == 3) {
                    isConnected = (edgeStatus[Tile.DOWN] == Tile.RAIL);
                }
                break;
            case Exit.LEFT:
                if (row == 1 || row == 5) {
                    isConnected = (edgeStatus[Tile.LEFT] == Tile.RAIL);
                } else if (row == 3) {
                    isConnected = (edgeStatus[Tile.LEFT] == Tile.HIGH);
                }
                break;
            default:
                break;
        }

        return isConnected;
    }

    /**
     * Check tile' edges relationship with exits.
     * - A highway connect to a railway;
     * - A railway connect to a highway.
     *
     * @param tile, the tile instance.
     * @param exitDirection, (UP, RIGHT, DOWN, LEFT).
     * @return true if illegally connected to the exit.
     */
    public static boolean isIllegalConnectedExit(Tile tile, int exitDirection) {
        boolean isIllegal = false;
        int[] edgeStatus = tile.getCurrentEdgesStatus();
        int row = tile.getRowPos();
        int col = tile.getColPos();

        switch (exitDirection) {
            case Exit.UP:
                if (col == 1 || col == 5) {
                    isIllegal = (edgeStatus[Tile.UP] == Tile.RAIL);
                } else if (col == 3) {
                    isIllegal = (edgeStatus[Tile.UP] == Tile.HIGH);
                }
                break;
            case Exit.RIGHT:
                if (row == 1 || row == 5) {
                    isIllegal = (edgeStatus[Tile.RIGHT] == Tile.HIGH);
                } else if (row == 3) {
                    isIllegal = (edgeStatus[Tile.RIGHT] == Tile.RAIL);
                }
                break;
            case Exit.DOWN:
                if (col == 1 || col == 5) {
                    isIllegal = (edgeStatus[Tile.DOWN] == Tile.RAIL);
                } else if (col == 3) {
                    isIllegal = (edgeStatus[Tile.DOWN] == Tile.HIGH);
                }
                break;
            case Exit.LEFT:
                if (row == 1 || row == 5) {
                    isIllegal = (edgeStatus[Tile.LEFT] == Tile.HIGH);
                } else if (row == 3) {
                    isIllegal = (edgeStatus[Tile.LEFT] == Tile.RAIL);
                }
                break;
            default:
                break;
        }

        return isIllegal;
    }

    /**
     * Validate the connection for the tile in the current position in the board.
     *
     * @param tileBoard the whole game board with existing tiles.
     * @param newTile the new tile going to be placed into the board with specific position and orientation.
     * @author Wyman
     *
     * @return true if the tile can be successfully placed into the specific position;
     *         false if not.
     */
    public static boolean isValidTileStrPlacement(Board tileBoard, Tile newTile) {
        int rowIdx = newTile.getRowPos();
        int colIdx = newTile.getColPos();
        boolean isIllegal = false;
        boolean isConnected = false;
        Tile tmpTile;

        if (tileBoard.getTileObjByPosition(rowIdx, colIdx) != null) { // This position has been already placed.
            return false;
        }

        if (rowIdx > 0) { // Tile may have upper neighbour.
            if (tileBoard.getTileObjByPosition(rowIdx - 1, colIdx) != null) {
                tmpTile = tileBoard.getTileObjByPosition(rowIdx - 1, colIdx);
                isIllegal |= areIllegalConnectedNeighbours(newTile, tmpTile);
                isConnected |= areConnectedNeighbours(newTile.getPlacementStr(), tmpTile.getPlacementStr());
            }
        }
        else {
            isIllegal |= isIllegalConnectedExit(newTile, Tile.UP);
            isConnected |= isConnectedExit(newTile, Tile.UP);
        }

        if (rowIdx < Board.BOARD_ROWS_NUM - 1) { // Tile may have lower neighbour.
            if (tileBoard.getTileObjByPosition(rowIdx + 1, colIdx) != null) {
                tmpTile = tileBoard.getTileObjByPosition(rowIdx + 1, colIdx);
                isIllegal |= areIllegalConnectedNeighbours(newTile, tmpTile);
                isConnected |= areConnectedNeighbours(newTile.getPlacementStr(), tmpTile.getPlacementStr());
            }
        }
        else {
            isIllegal |= isIllegalConnectedExit(newTile, Tile.DOWN);
            isConnected |= isConnectedExit(newTile, Tile.DOWN);
        }

        if (colIdx > 0) { // Tile may have left neighbour.
            if (tileBoard.getTileObjByPosition(rowIdx, colIdx - 1) != null) {
                tmpTile = tileBoard.getTileObjByPosition(rowIdx, colIdx - 1);
                isIllegal |= areIllegalConnectedNeighbours(newTile, tmpTile);
                isConnected |= areConnectedNeighbours(newTile.getPlacementStr(), tmpTile.getPlacementStr());
            }
        }
        else {
            isIllegal |= isIllegalConnectedExit(newTile, Tile.LEFT);
            isConnected |= isConnectedExit(newTile, Tile.LEFT);
        }
        if (colIdx < Board.BOARD_COLS_NUM - 1) { // Tile may have right neighbour.
            if (tileBoard.getTileObjByPosition(rowIdx, colIdx + 1) != null) {
                tmpTile = tileBoard.getTileObjByPosition(rowIdx, colIdx + 1);
                isIllegal |= areIllegalConnectedNeighbours(newTile, tmpTile);
                isConnected |= areConnectedNeighbours(newTile.getPlacementStr(), tmpTile.getPlacementStr());
            }
        }
        else {
            isIllegal |= isIllegalConnectedExit(newTile, Tile.RIGHT);
            isConnected |= isConnectedExit(newTile, Tile.RIGHT);
        }

        // Return the final result.
        return (!isIllegal && isConnected);
    }

    /**
     * Given a well-formed board string representing an ordered list of placements,
     * determine whether the board string is valid.
     * A board string is valid if each tile placement is legal with respect to all previous tile
     * placements in the string, according to the rules for legal placements:
     * - A tile must be placed such that at least one edge connects to either an exit or a pre-existing route.
     *   Such a connection is called a valid connection.
     * - Tiles may not be placed such that a highway edge connects to a railway edge;
     *   this is referred to as an invalid connection.
     *   Highways and railways may only join at station tiles.
     * - A tile may have one or more edges touching a blank edge of another tile;
     *   this is referred to as disconnected, but the placement is still legal.
     *
     * @param boardString a board string representing some placement sequence
     * @return true if placement sequence is valid
     */
    public static boolean isValidPlacementSequence(String boardString) {
        // FIXME Task 6: determine whether the given placement sequence is valid

        Board gameBoard = new Board();
        String tmpPlacement;
        boolean isValid;

        for (int i = 0; i < boardString.length(); i += 5) {
            tmpPlacement = boardString.substring(i, i + 5);
            Tile newTile = new Tile(tmpPlacement);

            isValid = isValidTileStrPlacement(gameBoard, newTile);
            if (isValid) {
                gameBoard.placeTileOnBoard(newTile);
            } else {
                return false;
            }
        }

        // All tiles can be successfully placed into the board.
        return true;
    }

    /**
     * Generate a random dice roll as a string of eight characters.
     * Dice A should be rolled three times, dice B should be rolled once.
     * Die A has faces numbered 0-5.
     * Die B has faces numbered 0-2.
     * Each die roll is composed of a character 'A' or 'B' representing the dice,
     * followed by a digit character representing the face.
     *
     * @return a String representing the die roll e.g. A0A4A3B2
     */
    public static String generateDiceRoll() {
        // FIXME Task 7: generate a dice roll
        Random rand = new Random();
        // String representing the die roll
        String result = "";
        int i;
        for (int k = 0; k < 4; k++){
            if(k==3){
                //Die B has faces numbered 0-2.
                i = rand.nextInt(3);
                result += ("B" + i);
            } else {
                // Die A has faces numbered 0-5
                i = rand.nextInt(6);
                result += ("A" + i);
            }
        }
        return result;
    }

    /**
     * Given the current state of a game board, output an integer representing the sum of all the following factors
     * that contribute to the player's final score.
     * <p>
     * * Number of exits mapped
     * * Number of centre tiles used
     * * Number of dead ends in the network
     *
     * @param boardString a board string representing a completed game
     * @return integer (positive or negative) for score *not* considering longest rail/highway
     */
    public static int getBasicScore(String boardString) {
        // FIXME Task 8: compute the basic score
        Board gameBoard = new Board();
        String tmpPlacement;

        for (int i = 0; i < boardString.length(); i += 5) {
            tmpPlacement = boardString.substring(i, i + 5);
            Tile newTile = new Tile(tmpPlacement);

            if (isValidTileStrPlacement(gameBoard, newTile)) {
                gameBoard.placeTileOnBoard(newTile);
            }
        }

        int sumScore = 0;
        Score score = new Score(gameBoard);
        score.initGraphs();
        sumScore += score.getExitsScore();
        sumScore += score.getCenterTilesScore();
        sumScore -= score.getEdgeError();

        return sumScore;
    }

    /**
     * Try each empty position in the gameboard to generate a valid placement for the new tile.
     *
     * @param gameBoard the board to place tile into.
     * @param newTile the tile need to be placed.
     * @author Wyman
     *
     * @return true if the tile get a position to place into.
     *         false if not.
     */
    public static boolean isPlacedTryAllBoardPos(Board gameBoard, Tile newTile) {
        String cRow;
        String cCol;
        for (int pos = 0; pos < 49; pos++) {
            // Update pos in newTile.
            cRow = String.valueOf((char)('A' + pos/7));
            cCol = String.valueOf((char)('0' + pos%7));
            newTile.pos = cRow + cCol;

            if (isValidTileStrPlacement(gameBoard, newTile)) {
                // Update the gameBoard string and add new tile to the board.
                gameBoard.placeTileOnBoard(newTile);
                return true;
            }
        }

        return false;
    }

    /**
     * Given a valid boardString and a dice roll for the round,
     * return a String representing an ordered sequence of valid piece placements for the round.
     * @param boardString a board string representing the current state of the game as at the start of the round
     * @param diceRoll a String representing a dice roll for the round
     * @return a String representing an ordered sequence of valid piece placements for the current round
     * @see RailroadInk#generateDiceRoll()
     */
    public static String generateMove(String boardString, String diceRoll) {
        // FIXME Task 10: generate a valid move
        Board gameBoard = new Board();
        String tmpPlacement;

        boolean isPlaced;  // A mark to check whether any new tile has been placed in this iteration.
        String remainTileMark = diceRoll;  // A string used to record the remaining tiles after placing into the board.
        String moveStr = "";

        for (int i = 0; i < boardString.length(); i += 5) {
            tmpPlacement = boardString.substring(i, i + 5);
            Tile newTile = new Tile(tmpPlacement);

            gameBoard.placeTileOnBoard(newTile);
        }

        do {
            // Update the diceRoll for this iteration after placing.
            isPlaced = false;

            for (int nameIdx = 0; nameIdx < remainTileMark.length(); nameIdx += 2) {
                // The string of position and orientation is spaces.
                String name = remainTileMark.substring(nameIdx, nameIdx+2);
                if (name.equals("  ")) {
                    continue;
                }

                Tile newTile = new Tile(name + "   ");
                for (char orient = '0'; orient <= '7'; orient++) {
                    newTile.orientation = orient;
                    if (isPlacedTryAllBoardPos(gameBoard, newTile)) {
                        moveStr = moveStr + newTile.getPlacementStr();
                        isPlaced = true;

                        // Edit the diceRoll string.
                        try {
                            remainTileMark = remainTileMark.substring(0, nameIdx) + "  " + remainTileMark.substring(nameIdx + 2);
                        } catch (IndexOutOfBoundsException e) {
                            remainTileMark = remainTileMark.substring(0, nameIdx) + "  ";
                        }
                        break;
                    }
                }
            }
        } while (isPlaced);

        return moveStr;
    }

    /**
     * Given the current state of a game board, output an integer representing the sum of all the factors contributing
     * to `getBasicScore`, as well as those attributed to:
     * <p>
     * * Longest railroad
     * * Longest highway
     *
     * @param boardString a board string representing a completed game
     * @return integer (positive or negative) for final score (not counting expansion packs)
     */
    public static int getAdvancedScore(String boardString) {
        // FIXME Task 12: compute the total score including bonus points
        return getBasicScore(boardString);
    }
}

