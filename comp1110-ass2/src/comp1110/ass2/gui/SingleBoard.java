package comp1110.ass2.gui;

import comp1110.ass2.Board;
import comp1110.ass2.RailroadInk;
import comp1110.ass2.Score;
import comp1110.ass2.Tile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


class SingleBoard extends Layers{
    private static final int SPECIAL_TILES_NUM = 6;
    private static final int ROLL_DICE_TILES_NUM = 4;

    private final Group ctrlGroup = new Group();
    private final Group imgGroup = new Group();
    private final Group roundImgGroup = new Group();

    protected Board singleBoard;
    private Label roundSpe;
    Button nextRound;
    String diceRollStr;

    SingleBoard(Scene menuScene,
                int boardLayoutX, int boardLayoutY,
                int specialLayoutX, int specialLayoutY,
                int choiceLayoutX, int choiceLayoutY,
                int fitHeight, int fitWidth) {
        super(menuScene, boardLayoutX, boardLayoutY, fitHeight, fitWidth);

        root.getChildren().add(ctrlGroup);
        root.getChildren().add(imgGroup);
        root.getChildren().add(roundImgGroup);

        singleBoard = new Board();
        makeSpecialBoard(specialLayoutX, specialLayoutY, fitHeight, fitWidth);
        makeChoiceBoard(choiceLayoutX, choiceLayoutY, fitHeight);
    }

    /**
     * When dragging the tile over the board, this method will return the detection result
     * that whether the tile is inside the area of the board.
     *
     * @param xPos the x layout of tile currently dragging over the board.
     * @param yPos the y layout of tile currently dragging over the board.
     * @return true if this tile is inside the area of the board.
     *         false if not.
     */
    private boolean isTileInsideBoard(double xPos, double yPos) {
        // Check whether the tile in in the area of the board.
        return ((xPos > layoutX)
                && (xPos <= layoutX + fitWidth * BOARD_COLS)
                && (yPos > layoutY)
                && (yPos < layoutY + fitHeight * BOARD_ROWS));
    }

    /**
     * Setting the mouse events for scolling, pressing, dragging and release.
     *
     * @param tile tile object for operation.
     * @param roundSpe show the number of used special tiles in this round.
     * @param gameSpe show the number of used special tiles in this game.
     *
     * @author Wyman
     */
    private void makeSpecialMouseEvent(Tile tile, Label roundSpe, Label gameSpe) {
        ImageView imgView = tile.getImgView();

        imgView.setOnScroll(me -> {
            if ((!singleBoard.isSpeTileUsedInRound()) && (singleBoard.getUsedSpeTileNum() < 3)) {
                if (me.getDeltaY() < 0) {
                    tile.rotate90Degree();
                    imgView.setRotate(90 * tile.getOrientation());
                } else if (me.getDeltaY() > 0) {
                    tile.rotate270Degree();
                    imgView.setRotate(90 * tile.getOrientation());
                }
            }
        });
        imgView.setOnMousePressed(me -> {
            if ((!singleBoard.isSpeTileUsedInRound()) && (singleBoard.getUsedSpeTileNum() < 3)) {
                if (me.getButton() == MouseButton.PRIMARY) {  // Mouse left click.
                    tile.deltaX = me.getSceneX() - tile.imgLayoutX;
                    tile.deltaY = me.getSceneY() - tile.imgLayoutY;
                } else if (me.getButton() == MouseButton.SECONDARY) {  // Mouse right click.
                    tile.flipOverY();
                    tile.placeImgView();
                }
            }
        });
        imgView.setOnMouseDragged(me -> {
            if (me.getButton() == MouseButton.PRIMARY) {  // Mouse left click.
                if ((!singleBoard.isSpeTileUsedInRound()) && (singleBoard.getUsedSpeTileNum() < 3)) {
                    imgView.setLayoutX(me.getSceneX() - tile.deltaX);
                    imgView.setLayoutY(me.getSceneY() - tile.deltaY);
                }
            }
        });
        imgView.setOnMouseReleased(me -> {
            if (me.getButton() == MouseButton.PRIMARY) {  // Mouse left click.
                if ((!singleBoard.isSpeTileUsedInRound()) && (singleBoard.getUsedSpeTileNum() < 3)) {
                    if (isTileInsideBoard(imgView.getLayoutX() + fitWidth/2, imgView.getLayoutY() + fitHeight/2)) {
                        int col = (int)((imgView.getLayoutX() + fitWidth/2 - this.layoutX) / fitWidth);
                        int row = (int)((imgView.getLayoutY() + fitHeight/2 - this.layoutY) / fitHeight);

                        String cRow = String.valueOf((char)('A' + row));
                        String cCol = String.valueOf((char)('0' + col));
                        tile.pos = cRow + cCol;

                        if (RailroadInk.isValidTileStrPlacement(singleBoard, tile)) {
                            singleBoard.placeTileOnBoard(tile);
                            tile.imgLayoutX = this.layoutX + col * fitWidth + 1;
                            tile.imgLayoutY = this.layoutY + row * fitHeight + 1;
                            imgView.setLayoutX(tile.imgLayoutX);
                            imgView.setLayoutY(tile.imgLayoutY);
                            imgView.setDisable(true);

                            roundSpe.setText("Special Tiles: 0 / 1 available in this round.");
                            gameSpe.setText("Special Tiles: " + (3 - singleBoard.getUsedSpeTileNum()) + " / 3 available in this game.");
                        } else {
                            tile.pos = "  ";
                            imgView.setLayoutX(tile.imgLayoutX);
                            imgView.setLayoutY(tile.imgLayoutY);
                        }
                    } else {
                        imgView.setLayoutX(tile.imgLayoutX);
                        imgView.setLayoutY(tile.imgLayoutY);
                    }
                }
            }
        });
    }

    /**
     * Draw a 1*6 area for special tiles above the board.
     *
     * @param layoutX the x-axis position in the scene.
     * @param layoutY the x-axis position in the scene.
     * @param fitHeight the image view height in the scene.
     * @param fitWidth the image view width in the scene.
     *
     * @author Wyman
     */
    private void makeSpecialBoard(int layoutX, int layoutY, int fitHeight, int fitWidth) {
        roundSpe = new Label("Special Tiles: 1 / 1 available in this round.");
        roundSpe.setTextFill(Color.BLUE);
        roundSpe.setLayoutX(layoutX + fitWidth);
        roundSpe.setLayoutY(layoutY - 40);
        ctrlGroup.getChildren().add(roundSpe);

        Label gameSpe = new Label("Special Tiles: 3 / 3 available in this game.");
        gameSpe.setTextFill(Color.RED);
        gameSpe.setLayoutX(layoutX + fitWidth);
        gameSpe.setLayoutY(layoutY - 20);
        ctrlGroup.getChildren().add(gameSpe);

        int offset = 10;  // Leave padding space between tiles.
        // Place tile imageView.
        for (int idx = 0; idx < SPECIAL_TILES_NUM; idx++) {
            String imgPath = URI_BASE + "S" + idx + ".png";

            Tile tile = new Tile(imgPath);
            ImageView imgView = tile.getImgView();

            tile.imgLayoutX = layoutX + idx * fitWidth + idx * offset;
            tile.imgLayoutY = layoutY;

            imgView.setLayoutX(tile.imgLayoutX);
            imgView.setLayoutY(tile.imgLayoutY);
            imgView.setFitHeight(fitHeight);
            imgView.setFitWidth(fitWidth);
            imgView.setPreserveRatio(true);
            imgView.setPickOnBounds(true);

            makeSpecialMouseEvent(tile, roundSpe, gameSpe);

            imgGroup.getChildren().add(imgView);
        }
    }

    /**
     * 1. Receive the diceRoll from generateDiceRoll();
     * 2. Create Tile instance for choices of new round.
     *
     * @param tile tile instance with the relevant image view.
     * @author Wyman
     */
    private void makeChoicesMouseEvents(Tile tile) {
        ImageView imgView = tile.getImgView();

        imgView.setOnScroll(me -> {
            if (me.getDeltaY() < 0) {
                tile.rotate90Degree();
                imgView.setRotate(90 * tile.getOrientation());
            } else if (me.getDeltaY() > 0) {
                tile.rotate270Degree();
                imgView.setRotate(90 * tile.getOrientation());
            }
        });
        imgView.setOnMousePressed(me -> {
            if (me.getButton() == MouseButton.PRIMARY) {  // Mouse left click.
                tile.deltaX = me.getSceneX() - tile.imgLayoutX;
                tile.deltaY = me.getSceneY() - tile.imgLayoutY;
            } else if (me.getButton() == MouseButton.SECONDARY) {  // Mouse right click.
                tile.flipOverY();
                tile.placeImgView();
            }
        });
        imgView.setOnMouseDragged(me -> {
            if (me.getButton() == MouseButton.PRIMARY) {  // Mouse left click.
                imgView.setLayoutX(me.getSceneX() - tile.deltaX);
                imgView.setLayoutY(me.getSceneY() - tile.deltaY);
            }
        });
        imgView.setOnMouseReleased(me -> {
            if (me.getButton() == MouseButton.PRIMARY) {  // Mouse left click.
                if (isTileInsideBoard(imgView.getLayoutX() + fitWidth/2, imgView.getLayoutY() + fitHeight/2)) {
                    int col = (int)((imgView.getLayoutX() + fitWidth/2 - this.layoutX) / fitWidth);
                    int row = (int)((imgView.getLayoutY() + fitHeight/2 - this.layoutY) / fitHeight);

                    String cRow = String.valueOf((char)('A' + row));
                    String cCol = String.valueOf((char)('0' + col));
                    tile.pos = cRow + cCol;

                    if (RailroadInk.isValidTileStrPlacement(singleBoard, tile)) {
                        singleBoard.placeTileOnBoard(tile);
                        tile.imgLayoutX = this.layoutX + col * fitWidth + 1;
                        tile.imgLayoutY = this.layoutY + row * fitHeight + 1;
                        imgView.setLayoutX(tile.imgLayoutX);
                        imgView.setLayoutY(tile.imgLayoutY);
                        imgView.setDisable(true);

                        roundImgGroup.getChildren().remove(imgView);
                        imgGroup.getChildren().add(imgView);
                    } else {
                        tile.pos = "  ";
                        imgView.setLayoutX(tile.imgLayoutX);
                        imgView.setLayoutY(tile.imgLayoutY);
                    }
                } else {
                    imgView.setLayoutX(tile.imgLayoutX);
                    imgView.setLayoutY(tile.imgLayoutY);
                }
            }
        });
    }

    /**
     *  Make dice roll tiles for each round.
     *
     * @param layoutX the x-axis position in the scene.
     * @param layoutY the y-axis position in the scene.
     *
     * @author Wyman
     */
    private void create4ChoiceImg(int layoutX, int layoutY) {
        int offset = 30;  // Leave padding space between tiles.
        diceRollStr = RailroadInk.generateDiceRoll();

        for (int idx = 0; idx < 2 * ROLL_DICE_TILES_NUM; idx += 2) {
            String imgPath = URI_BASE + diceRollStr.substring(idx, idx + 2) + ".png";

            Tile tile = new Tile(imgPath);
            ImageView imgView = tile.getImgView();

            tile.imgLayoutX = layoutX;
            tile.imgLayoutY = layoutY + idx/2.0 * fitHeight + idx/2.0 * offset;

            imgView.setLayoutX(tile.imgLayoutX);
            imgView.setLayoutY(tile.imgLayoutY);
            imgView.setFitHeight(fitHeight);
            imgView.setFitWidth(fitWidth);
            imgView.setPreserveRatio(true);
            imgView.setPickOnBounds(true);

            // Place tile imageView into the board pane.
            makeChoicesMouseEvents(tile);

            roundImgGroup.getChildren().add(imgView);
        }
    }

    /**
     * Draw a 4*1 area for roll dice tiles on the right of the board.
     * There are 1 label, 4 tiles and 1 button in totals
     *
     * @param layoutX the x-axis position in the scene.
     * @param layoutY the x-axis position in the scene.
     * @param fitHeight the image view height in the scene.
     *
     * @author Wyman
     */
    private void makeChoiceBoard(int layoutX, int layoutY, int fitHeight) {
        int offset = 30;  // Leave padding space between tiles.

        // Make a label to count current round.
        Label roundLabel = new Label("Round 1");
        roundLabel.setTextFill(Color.BLUE);
        roundLabel.setLayoutX(layoutX + 10);
        roundLabel.setLayoutY(layoutY - fitHeight);
        ctrlGroup.getChildren().add(roundLabel);

        // Make a button for finish this round.
        nextRound = new Button("Next Round");
        nextRound.setLayoutX(layoutX);
        nextRound.setLayoutY(layoutY + ROLL_DICE_TILES_NUM * fitHeight + ROLL_DICE_TILES_NUM * offset);
        nextRound.setPadding(new Insets(5, 5, 5, 5));

        nextRound.setOnAction(e -> {
            if (singleBoard.getThisRound() <= 7) {
                singleBoard.nextRound();
                if (singleBoard.getUsedSpeTileNum() < 3) {
                    singleBoard.resetSpeUsedFlag();
                }
            }
            if (singleBoard.getThisRound() > 7) {
                // Pop up the scoring window and reset the game with OK button.
                Score score = new Score(singleBoard);
                score.initGraphs();

                gameOverWindow(score.getExitsScore(), score.getCenterTilesScore(), score.getEdgeError());
            } else {
                roundImgGroup.getChildren().clear();
                create4ChoiceImg(layoutX, layoutY);
                roundLabel.setText("Round " + singleBoard.getThisRound());
                if (singleBoard.getUsedSpeTileNum() < 3) {
                    roundSpe.setText("Special Tiles: 1 / 1 available in this round.");
                }
            }
        });

        create4ChoiceImg(layoutX, layoutY);
        controls.getChildren().add(nextRound);
    }

    /**
     * When finish the 7th round, game is over and pop up the final result.
     *
     * @author Wyman
     */
    private void gameOverWindow(int exitScore, int centerScore, int error) {
        Stage gameOver = new Stage();
        gameOver.setTitle("Game Over!");
        gameOver.initModality(Modality.APPLICATION_MODAL);

        GridPane scoreTable = new GridPane();
        scoreTable.setAlignment(Pos.CENTER);
        // Scoring table.
        for(int i = 0; i < 2; i++) {
            ColumnConstraints column = new ColumnConstraints(200);
            scoreTable.getColumnConstraints().add(column);
        }
        // Five scoring rules and 2 rows for titles and button.
        for(int j = 0; j < (5 + 2); j++) {
            RowConstraints row = new RowConstraints(50);
            scoreTable.getRowConstraints().add(row);
        }

        // First row is used as titles.
        Label rule = new Label("Rules");
        rule.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(rule, 0, 0);
        scoreTable.getChildren().add(rule);

        Label score = new Label("Score");
        score.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(score, 1, 0);
        scoreTable.getChildren().add(score);

        // Second row is about connected exits score.
        Label connectedExit = new Label("Connected Exits");
        connectedExit.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(connectedExit, 0, 1);
        scoreTable.getChildren().add(connectedExit);

        Label exitScoreLabel = new Label(Integer.toString(exitScore));
        exitScoreLabel.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(exitScoreLabel, 1, 1);
        scoreTable.getChildren().add(exitScoreLabel);

        // Third row is about center tiles score.
        Label centerTiles = new Label("Center Tiles");
        centerTiles.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(centerTiles, 0, 2);
        scoreTable.getChildren().add(centerTiles);

        Label tileScore = new Label(Integer.toString(centerScore));
        tileScore.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(tileScore, 1, 2);
        scoreTable.getChildren().add(tileScore);

        // Fourth row is about error score.
        Label errorLabel = new Label("Errors");
        errorLabel.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(errorLabel, 0, 3);
        scoreTable.getChildren().add(errorLabel);

        Label errorScore = new Label("-" + error);
        errorScore.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(errorScore, 1, 3);
        scoreTable.getChildren().add(errorScore);

        // Setting for "OK" button to reset the game and scene.
        Button ok = new Button("OK");
        ok.setAlignment(Pos.CENTER);
        ok.setPadding(new Insets(10, 25, 10, 25));
        scoreTable.setConstraints(ok, 1, 6);
        scoreTable.getChildren().add(ok);
        ok.setOnAction(e -> {
            gameOver.close();
            Game.mainWindow.setScene(menuScene);
        });

        Scene scene = new Scene(scoreTable, 500, 600);
        gameOver.setScene(scene);
        gameOver.showAndWait();
    }
}
