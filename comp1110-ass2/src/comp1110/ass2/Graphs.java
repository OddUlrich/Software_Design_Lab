package comp1110.ass2;


import java.util.HashMap;

class Graphs {
    private int graphId;
    Node head;
    private int exitNum;

    private HashMap<String, Node> nodeMap;
    private Board gameBoard;

    Graphs(Tile tile, int graphId, Board gameBoard) {
        this.graphId = graphId;
        this.gameBoard = gameBoard;

        head = new Node(tile);

        nodeMap = new HashMap<>();
        nodeMap.put(tile.pos, head);
    }

    /**
     * Return the current number of exits in to this graph.
     *
     * @author Wyman
     * @return the number of exits.
     */
    int getExitNum() {
        return exitNum;
    }

    /**
     * Inner class for data structure operations.
     */
    class Node {
        Tile tile;
        Node up, right, down, left;  // Neighbour connected tiles.

        Node(Tile tile) {
            this.tile = tile;
            if (!tile.name.equals("B2")) {
                tile.graphId = graphId;
            }

            // Check the connection with exit when creating a new node.
            if ((RailroadInk.isConnectedExit(tile, Exit.UP))
                || (RailroadInk.isConnectedExit(tile, Exit.RIGHT))
                || (RailroadInk.isConnectedExit(tile, Exit.DOWN))
                || (RailroadInk.isConnectedExit(tile, Exit.LEFT))) {
                Exit exit = gameBoard.getExitWithPos(tile.getRowPos(), tile.getColPos());
                if (exit != null) {
                    if (tile.name.equals("B2")) {
                        switch (exit.orientation) {
                            case Exit.UP:
                                tile.upId = graphId;
                                break;
                            case Exit.RIGHT:
                                tile.rightId = graphId;
                                break;
                            case Exit.DOWN:
                                tile.downId = graphId;
                                break;
                            case Exit.LEFT:
                                tile.leftId = graphId;
                                break;
                            default:
                                System.err.println("Invalid exit orientation: " + exit.orientation);
                                break;
                        }
                    }
                    if (exit.graphId == 0) {
                        exit.graphId = graphId;
                        exitNum++;
                    }
                }
            }
        }

        /**
         * Find neighbour tiles for the current starting tile in a graph.
         *
         * @param board game board instance with tiles placement
         * @author Wyman
         */
        void findNeighbourNode(Board board) {
            this.findUp(board);
            this.findRight(board);
            this.findDown(board);
            this.findLeft(board);
        }

        /**
         * Find the upper tile and check whether it is connected to the current tile.
         *
         * @param board game board instance with tiles placement
         */
        void findUp(Board board) {
            if (tile.getRowPos() != 0) {
                Tile upTile = board.getTileObjByPosition(tile.getRowPos() - 1, tile.getColPos());
                if (upTile != null) {
                    if (RailroadInk.areConnectedNeighbours(tile.getPlacementStr(), upTile.getPlacementStr())) {
                        Node upNode;
                        if ((upTile.graphId > 0) || ((upTile.graphId == -1) && (upTile.upId == graphId))) {
                            upNode = nodeMap.get(upTile.pos);
                        } else {
                            upNode = new Node(upTile);
                            if (upTile.name.equals("B2")) {
                                upTile.downId = graphId;
                            }
                            nodeMap.put(upTile.pos, upNode);
                        }

                        // Update the connection relationship.
                        this.up = upNode;
                        upNode.down = this;
                        if (this.tile.name.equals("B2")) {
                            this.tile.upId = graphId;
                        }

                        if (upNode.up == null) {
                            upNode.findUp(board);
                        }
                        if ((!upTile.name.equals("B2")) && (upNode.right == null)) {
                            upNode.findRight(board);
                        }
                        if ((!upTile.name.equals("B2")) && (upNode.left == null)) {
                            upNode.findLeft(board);
                        }
                    }
                }
            }
        }

        /**
         * Find the right tile and check whether it is connected to the current tile.
         *
         * @param board game board instance with tiles placement
         */
        void findRight(Board board) {
            if (tile.getColPos() != 6) {
                Tile rightTile = board.getTileObjByPosition(tile.getRowPos(), tile.getColPos() + 1);
                if (rightTile != null) {
                    if (RailroadInk.areConnectedNeighbours(tile.getPlacementStr(), rightTile.getPlacementStr())) {
                        Node rightNode;
                        if ((rightTile.graphId > 0) || ((rightTile.graphId == -1) && (rightTile.rightId == graphId))) {
                            rightNode = nodeMap.get(rightTile.pos);
                        } else {
                            rightNode = new Node(rightTile);
                            if (rightTile.name.equals("B2")) {
                                rightTile.leftId = graphId;
                            }
                            nodeMap.put(rightTile.pos, rightNode);
                        }

                        // Update the connection relationship.
                        this.right = rightNode;
                        rightNode.left = this;
                        if (this.tile.name.equals("B2")) {
                            this.tile.rightId = graphId;
                        }

                        if ((!rightTile.name.equals("B2")) && (rightNode.up == null)) {
                            rightNode.findUp(board);
                        }
                        if (rightNode.right == null) {
                            rightNode.findRight(board);
                        }
                        if ((!rightTile.name.equals("B2")) && (rightNode.down == null)) {
                            rightNode.findDown(board);
                        }
                    }
                }
            }
        }

        /**
         * Find the down tile and check whether it is connected to the current tile.
         *
         * @param board game board instance with tiles placement
         */
        void findDown(Board board) {
            if (tile.getRowPos() != 6) {
                Tile downTile = board.getTileObjByPosition(tile.getRowPos() + 1, tile.getColPos());
                if (downTile != null) {
                    if (RailroadInk.areConnectedNeighbours(tile.getPlacementStr(), downTile.getPlacementStr())) {
                        Node downNode;
                        if ((downTile.graphId > 0) || ((downTile.graphId == -1) && (downTile.downId == graphId))) {
                            downNode = nodeMap.get(downTile.pos);
                        } else {
                            downNode = new Node(downTile);
                            if (downTile.name.equals("B2")) {
                                downTile.upId = graphId;
                            }
                            nodeMap.put(downTile.pos, downNode);
                        }

                        // Update the connection relationship.
                        this.down = downNode;
                        downNode.up = this;
                        if (this.tile.name.equals("B2")) {
                            this.tile.downId = graphId;
                        }

                        if ((!downTile.name.equals("B2")) && (downNode.right == null)) {
                            downNode.findRight(board);
                        }
                        if (downNode.down == null) {
                            downNode.findDown(board);
                        }
                        if ((!downTile.name.equals("B2")) && (downNode.left == null)) {
                            downNode.findLeft(board);
                        }

                    }
                }
            }
        }

        /**
         * Find the left tile and check whether it is connected to the current tile.
         *
         * @param board game board instance with tiles placement
         */
        void findLeft(Board board) {
            if (tile.getColPos() != 0) {
                Tile leftTile = board.getTileObjByPosition(tile.getRowPos(), tile.getColPos() - 1);
                if (leftTile != null) {
                    if (RailroadInk.areConnectedNeighbours(tile.getPlacementStr(), leftTile.getPlacementStr())) {
                        Node leftNode;
                        if ((leftTile.graphId > 0) || ((leftTile.graphId == -1) && (leftTile.leftId == graphId))) {
                            leftNode = nodeMap.get(leftTile.pos);
                        } else {
                            leftNode = new Node(leftTile);
                            if (leftTile.name.equals("B2")) {
                                leftTile.rightId = graphId;
                            }
                            nodeMap.put(leftTile.pos, leftNode);
                        }

                        // Update the connection relationship.
                        this.left = leftNode;
                        leftNode.right = this;
                        if (this.tile.name.equals("B2")) {
                            this.tile.leftId = graphId;
                        }

                        if ((!leftTile.name.equals("B2")) && (leftNode.up == null)) {
                            leftNode.findUp(board);
                        }
                        if ((!leftTile.name.equals("B2")) && (leftNode.down == null)) {
                            leftNode.findDown(board);
                        }
                        if (leftNode.left == null) {
                            leftNode.findLeft(board);
                        }
                    }
                }
            }
        }
    }

}
