package comp1110.ass2.gui;

import comp1110.ass2.RailroadInk;
import comp1110.ass2.Score;
import comp1110.ass2.Tile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

class CompeteBoard extends SingleBoard {
    private String curStr = "";

    CompeteBoard(Scene menuScene,
                        int boardLayoutX, int boardLayoutY,
                        int specialLayoutX, int specialLayoutY,
                        int choiceLayoutX, int choiceLayoutY,
                        int fitHeight, int fitWidth) {
        super(menuScene, boardLayoutX, boardLayoutY,
                specialLayoutX, specialLayoutY,
                choiceLayoutX, choiceLayoutY,
                fitHeight, fitWidth);

        makeComponentBoard(boardLayoutX, boardLayoutY, fitHeight, fitWidth);
    }

    /**
     * Draw another 7*7 area for component board.
     *
     * @param layoutX the x-axis position in the scene.
     * @param layoutY the x-axis position in the scene.
     * @param fitHeight the image view height in the scene.
     * @param fitWidth the image view width in the scene.
     *
     * @author Wyman
     */
    private void makeComponentBoard(int layoutX, int layoutY, int fitHeight, int fitWidth) {
        int newX = layoutX + 11 * fitWidth;

        makeBoard(newX, layoutY, fitHeight, fitWidth);
        makeRedRectangle(newX, layoutY, fitHeight, fitWidth);
        makeExit(newX, layoutY, fitHeight, fitWidth);

        makeBoardLabel(layoutX, layoutY, fitHeight, fitWidth);

        // Add additional button action for the component board.
        nextRoundEvent();
    }

    /**
     * Make two labels under the relevant board respectively as a sign.
     *
     * @param layoutX the x-axis position in the scene.
     * @param layoutY the x-axis position in the scene.
     * @param fitHeight the image view height in the scene.
     * @param fitWidth the image view width in the scene.
     *
     * @author Wyman
     */
    private void makeBoardLabel(int layoutX, int layoutY, int fitHeight, int fitWidth) {
        Label playerLabel = new Label("Player Board");
        Label ComponentLabel = new Label("Component Board");

        playerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        playerLabel.setLayoutX(layoutX + 2*fitWidth);
        playerLabel.setLayoutY(layoutY + 8*fitHeight);
        controls.getChildren().add(playerLabel);

        ComponentLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        ComponentLabel.setLayoutX(layoutX + 13*fitWidth);
        ComponentLabel.setLayoutY(layoutY + 8*fitHeight);
        controls.getChildren().add(ComponentLabel);
    }

    /**
     * Keep the old button event and add new event into the button.
     *
     * @author Wyman
     */
    private void nextRoundEvent() {
        EventHandler<ActionEvent> curAction = nextRound.getOnAction();
        nextRound.setOnAction(e -> {
            curAction.handle(e);
            String newStr = RailroadInk.generateMove(curStr, diceRollStr);
            nextRoundPlacement(newStr);
            curStr = curStr + newStr;

            if (super.singleBoard.getThisRound() > 7) {
                // Pop up the scoring window and reset the game with OK button.
                Score playerScore = new Score(singleBoard);
                playerScore.initGraphs();
                Score npcScore = new Score(singleBoard);
                npcScore.initGraphs();
                gameOverWindow(playerScore, npcScore);
            }
        });
    }

    /**
     * Place new placed tile image view into the component board.
     *
     * @param newPlacement placement string for several new tiles.
     * @author Wyman
     */
    private void nextRoundPlacement(String newPlacement) {
        for (int idx = 0; idx < newPlacement.length(); idx += 5) {
            Tile tile = new Tile(newPlacement.substring(idx, idx + 5));
            String imgPath = URI_BASE + tile.name + ".png";

            // Create imgView into the board.
            Image image = new Image(Game.class.getResource(imgPath).toString());
            ImageView imgView = new ImageView(image);
            tile.setImgView(imgView);

            imgView.setFitHeight(fitHeight);
            imgView.setFitWidth(fitWidth);
            imgView.setPreserveRatio(true);
            tile.placeImgView();

            // Place tile imageView into the board pane.
            board.setConstraints(imgView, tile.getColPos(), tile.getRowPos());
            board.getChildren().add(imgView);
        }
    }

    /**
     * When finish the 7th round, game is over and pop up the final result.
     *
     * @author Wyman
     */
    private void gameOverWindow(Score playerScore, Score npcScore) {
        Stage gameOver = new Stage();
        gameOver.setTitle("Game Over!");
        gameOver.initModality(Modality.APPLICATION_MODAL);

        GridPane scoreTable = new GridPane();
        scoreTable.setAlignment(Pos.CENTER);
        // Scoring table.
        for(int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints(150);
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

        Label playScoreLabel = new Label("Player Score");
        playScoreLabel.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(playScoreLabel, 1, 0);
        scoreTable.getChildren().add(playScoreLabel);

        Label npcScoreLabel = new Label("NPC Score");
        npcScoreLabel.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(npcScoreLabel, 2, 0);
        scoreTable.getChildren().add(npcScoreLabel);

        // Second row is about connected exits score.
        Label connectedExit = new Label("Connected Exits");
        connectedExit.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(connectedExit, 0, 1);
        scoreTable.getChildren().add(connectedExit);

        Label playerExitScore = new Label(Integer.toString(playerScore.getExitsScore()));
        playerExitScore.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(playerExitScore, 1, 1);
        scoreTable.getChildren().add(playerExitScore);

        Label npcExitScore = new Label(Integer.toString(playerScore.getExitsScore()));
        npcExitScore.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(npcExitScore, 2, 1);
        scoreTable.getChildren().add(npcExitScore);

        // Third row is about center tiles score.
        Label centerTiles = new Label("Center Tiles");
        centerTiles.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(centerTiles, 0, 2);
        scoreTable.getChildren().add(centerTiles);

        Label tileScore = new Label(Integer.toString(playerScore.getCenterTilesScore()));
        tileScore.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(tileScore, 1, 2);
        scoreTable.getChildren().add(tileScore);

        Label npcTileScore = new Label(Integer.toString(playerScore.getCenterTilesScore()));
        npcTileScore.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(npcTileScore, 2, 2);
        scoreTable.getChildren().add(npcTileScore);

        // Fourth row is about error score.
        Label errorLabel = new Label("Errors");
        errorLabel.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(errorLabel, 0, 3);
        scoreTable.getChildren().add(errorLabel);

        Label errorScore = new Label("-" + playerScore.getEdgeError());
        errorScore.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(errorScore, 1, 3);
        scoreTable.getChildren().add(errorScore);

        Label npcErrorScore = new Label("-" + playerScore.getEdgeError());
        npcErrorScore.setAlignment(Pos.CENTER);
        scoreTable.setConstraints(npcErrorScore, 2, 3);
        scoreTable.getChildren().add(npcErrorScore);

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
