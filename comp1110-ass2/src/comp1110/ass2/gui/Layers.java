package comp1110.ass2.gui;

import comp1110.ass2.Exit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


class Layers {
    private static final int VIEWER_WIDTH = 1024;
    static final int VIEWER_HEIGHT = 768;
    static final int BOARD_ROWS = 7;
    static final int BOARD_COLS = 7;
    static final String URI_BASE = "assets/";

    final double layoutX;
    final double layoutY;
    final double fitHeight;
    final double fitWidth;

    final Group root = new Group();
    final Group controls = new Group();

    Scene menuScene;
    private Scene scene;
    protected GridPane board;

    /**
     * These two 6*3 matrices represent information on exits with xPos, yPos and orientation.
     * Iterate the matrices below to new an Exit instance.
     */
    private static final String[] EXITS_NAME = {"HighExit", "RailExit"};
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

    Layers(Scene menuScene, int layoutX, int layoutY, int fitHeight, int fitWidth) {
        this.menuScene = menuScene;
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        this.fitHeight = fitHeight;
        this.fitWidth = fitWidth;

        root.getChildren().add(controls);
        scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        scene.setFill(Color.LIGHTBLUE);
        makeBoard(layoutX, layoutY, fitHeight, fitWidth);
        makeRedRectangle(layoutX, layoutY, fitHeight, fitWidth);
        makeExit(layoutX, layoutY, fitHeight, fitWidth);
        makeReturnButton();
    }

    /**
     * Draw an exit layer to fix the edge of the board in the window.
     * Actually the exit layer consists of 1 horizontal layer and 1 vertical layer.
     * These two layers are both 8*8 size with a position offset to fix on the edge of the board.
     *     1. The horizontal one: x - offset;
     *     2. The vertical one: y - offset.
     *
     * The row position and column position are only used to make the viewer.
     * When used to check the connection with a specific tile, we use the logic position, which is fixed by method fixPosition().
     *
     * @param layoutX the x-axis position in the scene.
     * @param layoutY the x-axis position in the scene.
     * @param fitHeight the image view height in the scene.
     * @param fitWidth the image view width in the scene.
     *
     * @author Wyman
     * @see Exit
     */
    void makeExit(int layoutX, int layoutY, int fitHeight, int fitWidth) {
        GridPane horizontalExit = new GridPane();
        GridPane verticalExit = new GridPane();

        horizontalExit.setLayoutX(layoutX - fitWidth / 2.0);
        horizontalExit.setLayoutY(layoutY);
        verticalExit.setLayoutX(layoutX);
        verticalExit.setLayoutY(layoutY - fitHeight / 2.0);

        for(int i = 0; i < BOARD_COLS + 1; i++) {
            ColumnConstraints columnH = new ColumnConstraints(fitWidth);
            ColumnConstraints columnV = new ColumnConstraints(fitWidth);
            horizontalExit.getColumnConstraints().add(columnH);
            verticalExit.getColumnConstraints().add(columnV);
        }
        for(int j = 0; j < BOARD_ROWS + 1; j++) {
            RowConstraints rowH = new RowConstraints(fitHeight);
            RowConstraints rowV = new RowConstraints(fitHeight);
            horizontalExit.getRowConstraints().add(rowH);
            verticalExit.getRowConstraints().add(rowV);
        }

        for (String exitName: EXITS_NAME) {
            int[][] tmpExits;
            if (exitName.equals("HighExit")) {
                tmpExits = HIGHWAY_EXITS;
            } else if (exitName.equals("RailExit")) {
                tmpExits = RAILWAY_EXITS;
            } else {
                System.err.println("Invalid type of exit!");
                return;
            }

            for (int[] exit: tmpExits) {
                Exit newExit = new Exit(exit[0], exit[1], exit[2]);

                Image exitImage = new Image(Game.class.getResource(URI_BASE + exitName + ".png").toString());
                ImageView exitView = new ImageView(exitImage);

                exitView.setFitHeight(fitHeight);
                exitView.setFitWidth(fitWidth);
                exitView.setPreserveRatio(true);

                // Rotate the image.
                exitView.setRotate(90 * newExit.orientation);

                if (newExit.orientation % 2 == 0) { // UP or DOWN.
                    verticalExit.setConstraints(exitView, newExit.xPos, newExit.yPos);
                    verticalExit.getChildren().add(exitView);
                } else {  // LEFT or RIGHT.
                    horizontalExit.setConstraints(exitView, newExit.xPos, newExit.yPos);
                    horizontalExit.getChildren().add(exitView);
                }
            }
        }

        root.getChildren().add(horizontalExit);
        root.getChildren().add(verticalExit);
    }

    /**
     * Draw a 3*3 red rectangle area at the centre of the board.
     *
     * @param layoutX the x-axis position in the scene.
     * @param layoutY the x-axis position in the scene.
     * @param fitHeight the image view height in the scene.
     * @param fitWidth the image view width in the scene.
     *
     * @author Wyman
     */
    void makeRedRectangle(int layoutX, int layoutY, int fitHeight, int fitWidth) {
        GridPane redRectangle = new GridPane();

        RowConstraints rowRec = new RowConstraints(3 * fitHeight);
        redRectangle.getRowConstraints().add(rowRec);
        ColumnConstraints columnRec = new ColumnConstraints(3 * fitWidth);
        redRectangle.getColumnConstraints().add(columnRec);

        redRectangle.setLayoutX(layoutX + 2 * fitWidth);
        redRectangle.setLayoutY(layoutY + 2 * fitHeight);
        redRectangle.setStyle("-fx-border-radius: 5;"
                + "-fx-border-color: red;"
                + "-fx-grid-lines-visible: true;");

        root.getChildren().add(redRectangle);
    }

    /**
     * Draw a 7*7 board in the window.
     *
     * @param layoutX the x-axis position in the scene.
     * @param layoutY the x-axis position in the scene.
     * @param fitHeight the image view height in the scene.
     * @param fitWidth the image view width in the scene.
     *
     * @author Wyman
     */
    void makeBoard(int layoutX, int layoutY, int fitHeight, int fitWidth) {
        this.board = new GridPane();
        board.setLayoutX(layoutX);
        board.setLayoutY(layoutY);

        for(int i = 0; i < BOARD_COLS; i++) {
            ColumnConstraints column = new ColumnConstraints(fitWidth);
            board.getColumnConstraints().add(column);
        }
        for(int j = 0; j < BOARD_ROWS; j++) {
            RowConstraints row = new RowConstraints(fitHeight);
            board.getRowConstraints().add(row);
        }

        board.setStyle("-fx-border-radius: 5;"
                + "-fx-background-color: blanchedalmond ;"
                + "-fx-grid-lines-visible: true;");
        root.getChildren().add(board);
    }

    /**
     * Draw a return button to go back to menu page.
     *
     * @author Wyman
     */
    private void makeReturnButton() {
        Button returnBtn = new Button("Back to Menu");
        returnBtn.setLayoutX(20);
        returnBtn.setLayoutY(40);
        returnBtn.setLineSpacing(30);
        returnBtn.setPadding(new Insets(10, 20, 10, 20));
        returnBtn.setOnAction(e -> backToMenuAlert());
        controls.getChildren().add(returnBtn);
    }

    /**
     * This method will be called when the button of "Back to Menu" is pressed.
     * A prompt or an alert window will pop out to confirm the request.
     *
     * @author Wyman
     */
    private void backToMenuAlert() {
        Stage alertBack = new Stage();
        alertBack.setTitle("Confirm Back to the Menu");
        alertBack.initModality(Modality.APPLICATION_MODAL);

        // Setting for label.
        Label prompt = new Label("Exit the current mode and return to the menu?");
        prompt.setPadding(new Insets(20, 20, 20, 20));
        prompt.setAlignment(Pos.TOP_CENTER);

        // Setting for "yes" button. Close all windows.
        Button yes = new Button("Yes");
        yes.setAlignment(Pos.BOTTOM_LEFT);
        yes.setOnAction(e -> {
            alertBack.close();
            Game.mainWindow.setScene(menuScene);
        });

        // Setting for "No" button. Close the current window and return to previous window.
        Button no = new Button("No");
        no.setAlignment(Pos.BOTTOM_RIGHT);
        no.setOnAction(e -> alertBack.close());

        // HBox contains two buttons horizontally.
        HBox hb = new HBox();
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.getChildren().addAll(yes, no);

        // VBox contains the label and HBox vertically.
        VBox vb = new VBox();
        vb.setPadding(new Insets(10, 10, 20, 20));
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(prompt, hb);

        Scene scene = new Scene(vb, 400, 150);
        alertBack.setScene(scene);
        alertBack.showAndWait();
    }

    Scene getScene() {
        return this.scene;
    }
}
