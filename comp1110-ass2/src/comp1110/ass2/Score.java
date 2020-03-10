package comp1110.ass2;

import java.util.ArrayList;

public class Score {
    public static final int[] AWARD_FOR_CONNECTED_ROUTE = {0, 0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 45};

    // A list to store every starting node for the connected graph.
    // The index of each graph is equal to graphId - 1.
    private static ArrayList<Graphs> mapList;

    private Board board;

    public Score(Board board) {
        mapList = new ArrayList<>();
        this.board = board;
    }

    /**
     * Initialize a connected graph to store the tiles for scoring.
     *
     * @author Wyman
     */
    public void initGraphs() {
        int curId = 0;

        //  Iterate each exit to configure connected routes.
        for (Exit exit: board.getExitsArray()) {
            if (exit.graphId != 0) {
                // This exit has been added into a graph.
                continue;
            }

            Tile startTile = board.getTileObjByPosition(exit.getRowPos(), exit.getColPos());
            if ((startTile != null)
                    && ((startTile.graphId == 0) || (startTile.graphId == -1))
                    && (RailroadInk.isConnectedExit(startTile, exit.orientation))) {
                // Create a new graph with new id for the rest tiles connected to exits.
                curId++;

                // Assign the current graph id to the connected edge of B2 tile.
                if (startTile.name.equals("B2")) {
                    switch (exit.orientation) {
                        case Exit.UP:
                            startTile.upId = curId;
                            break;
                        case Exit.RIGHT:
                            startTile.rightId = curId;
                            break;
                        case Exit.DOWN:
                            startTile.downId = curId;
                            break;
                        case Exit.LEFT:
                            startTile.leftId = curId;
                            break;
                        default:
                            System.err.println("Invalid exit orientation: " + exit.orientation);
                            break;
                    }
                }

                Graphs graphHead = new Graphs(startTile, curId, board);
                mapList.add(graphHead);

                graphHead.head.findNeighbourNode(board);
            }
        }
    }

    /**
     * Calculate the exits mapping score of the basic score when finish 7 rounds game.
     * The number of connected exits is the index in AWARD_FOR_CONNECTED_ROUTE.
     *
     * Iterate each exit in exits in board.
     * If there are more than 1 exits in the same graphId, that means these exits are connected.
     *
     * @author Wyman
     * @return exits mapping score
     */
    public int getExitsScore() {
        int sumNum = 0;

        for (Graphs graph: mapList) {
            int tmpNum = graph.getExitNum();
            if (tmpNum > 1) {
                sumNum += AWARD_FOR_CONNECTED_ROUTE[tmpNum];
            }
        }
        return sumNum;
    }

    /**
     * Calculate the number of tiles that are placed in the red rectangle area of the board.
     *
     * @author Wyman
     * @return the number of tiles
     */
    public int getCenterTilesScore() {
        int sumNum = 0;

        for (int i = 2; i <= 4; i++) {
            for (int j = 2; j <= 4; j++) {
                Tile tmpTile = board.getTileObjByPosition(i, j);
                if (tmpTile != null) {
                    sumNum++;
                }
            }
        }
        return sumNum;
    }

    /**
     * Check each placed tile and calculate all unconnected edge of tiles.
     *
     * @author Wyman
     * @return the number of error;
     */
    public int getEdgeError() {
        int sumNum = 0;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Tile tmpTile = board.getTileObjByPosition(i, j);
                if (tmpTile != null) {
                    if (tmpTile.graphId >= 0) {
                        int[] edgeStatus = tmpTile.getCurrentEdgesStatus();

                        if ((tmpTile.getRowPos() != 0)
                                && (edgeStatus[Tile.UP] != Tile.BLANK)
                                && ((board.getTileObjByPosition(i-1, j) == null)
                                    || !(RailroadInk.areConnectedNeighbours(tmpTile.getPlacementStr(),
                                                                        board.getTileObjByPosition(i-1, j).getPlacementStr())))) {
                            sumNum++;
                        }
                        if ((tmpTile.getRowPos() != 6)
                                && (edgeStatus[Tile.DOWN] != Tile.BLANK)
                                && ((board.getTileObjByPosition(i+1, j) == null)
                                    || !(RailroadInk.areConnectedNeighbours(tmpTile.getPlacementStr(),
                                                                        board.getTileObjByPosition(i+1, j).getPlacementStr())))) {
                            sumNum++;
                        }
                        if ((tmpTile.getColPos() != 0)
                                && (edgeStatus[Tile.LEFT] != Tile.BLANK)
                                && ((board.getTileObjByPosition(i, j-1) == null)
                                    || !(RailroadInk.areConnectedNeighbours(tmpTile.getPlacementStr(),
                                                                        board.getTileObjByPosition(i, j-1).getPlacementStr())))) {
                            sumNum++;
                        }
                        if ((tmpTile.getColPos() != 6)
                                && (edgeStatus[Tile.RIGHT] != Tile.BLANK)
                                && ((board.getTileObjByPosition(i, j+1) == null)
                                    || !(RailroadInk.areConnectedNeighbours(tmpTile.getPlacementStr(),
                                                                        board.getTileObjByPosition(i, j+1).getPlacementStr())))) {
                            sumNum++;
                        }

                    } else if (tmpTile.graphId == -1){
                        // This tile is B2.
                        if ((tmpTile.getRowPos() != 0)
                                && ((board.getTileObjByPosition(i-1, j) == null)
                                    || !(RailroadInk.areConnectedNeighbours(tmpTile.getPlacementStr(),
                                                                            board.getTileObjByPosition(i-1, j).getPlacementStr())))) {
                            sumNum++;
                        }
                        if ((tmpTile.getColPos() != 6)
                                && ((board.getTileObjByPosition(i, j+1) == null)
                                    || !(RailroadInk.areConnectedNeighbours(tmpTile.getPlacementStr(),
                                                                            board.getTileObjByPosition(i, j+1).getPlacementStr())))) {
                            sumNum++;
                        }
                        if ((tmpTile.getRowPos() != 6)
                                && ((board.getTileObjByPosition(i+1, j) == null)
                                    || !(RailroadInk.areConnectedNeighbours(tmpTile.getPlacementStr(),
                                                                            board.getTileObjByPosition(i+1, j).getPlacementStr())))) {
                            sumNum++;
                        }
                        if ((tmpTile.getColPos() != 0)
                                && ((board.getTileObjByPosition(i, j-1) == null)
                                    || !(RailroadInk.areConnectedNeighbours(tmpTile.getPlacementStr(),
                                                                            board.getTileObjByPosition(i, j-1).getPlacementStr())))) {
                            sumNum++;
                        }
                    }
                }
            }
        }
        return sumNum;
    }
}
