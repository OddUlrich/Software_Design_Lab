package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Game extends Application {

    private static final int VIEWER_WIDTH = 1024;
    private static final int VIEWER_HEIGHT = 768;

    /* String placement parameters */
    private static final int BOARD_LAYOUT_X = 200;
    private static final int BOARD_LAYOUT_Y = 40;
    private static final int BOARD_FIT_HEIGHT = 80;
    private static final int BOARD_FIT_WIDTH = 80;

    /* Single play game parameters */
    private static final int GAME_LAYOUT_X = 150;
    private static final int GAME_LAYOUT_Y = 200;
    private static final int GAME_FIT_HEIGHT = 70;
    private static final int GAME_FIT_WIDTH = 70;
    private static final int SPECIAL_LAYOUT_X = 170;
    private static final int SPECIAL_LAYOUT_Y = 70;
    private static final int CHOICE_LAYOUT_X = 750;
    private static final int CHOICE_LAYOUT_Y = 250;

    /* Compete play game parameters */
    private static final int COMPETE_LAYOUT_X = 50;
    private static final int COMPETE_LAYOUT_Y = 240;
    private static final int COMPETE_FIT_HEIGHT = 50;
    private static final int COMPETE_FIT_WIDTH = 50;
    private static final int COMPETE_SPECIAL_LAYOUT_X = 70;
    private static final int COMPETE_SPECIAL_LAYOUT_Y = 150;
    private static final int COMPETE_CHOICE_LAYOUT_X = 460;
    private static final int COMPETE_CHOICE_LAYOUT_Y = 280;

    static Stage mainWindow;

    private final Group menuRoot = new Group();
    private final Group menuControls = new Group();
    private Scene menuScene = new Scene(menuRoot, VIEWER_WIDTH, VIEWER_HEIGHT);

    private Scene strScene;
    private Scene singleScene;
    private Scene competeScene;

    /**
     * Create a welcome page with buttons to choose modes.
     */
    private void makeControls() {
        Label title = new Label("Welcome to RailroadInk!");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        title.setTextFill(Color.RED);

        Button btnStrView = new Button("Placement String Viewer");
        btnStrView.setOnAction(e -> {
            StringViewer strBoard = new StringViewer(menuScene, BOARD_LAYOUT_X, BOARD_LAYOUT_Y, BOARD_FIT_HEIGHT, BOARD_FIT_WIDTH);
            strScene = strBoard.getScene();
            mainWindow.setScene(strScene);
        });

        Button btnSingle = new Button("Single Play");
        btnSingle.setOnAction(e -> {
            SingleBoard singleBoard = new SingleBoard(menuScene, GAME_LAYOUT_X, GAME_LAYOUT_Y,
                    SPECIAL_LAYOUT_X, SPECIAL_LAYOUT_Y,
                    CHOICE_LAYOUT_X, CHOICE_LAYOUT_Y,
                    GAME_FIT_HEIGHT, GAME_FIT_WIDTH);
            singleScene = singleBoard.getScene();
            mainWindow.setScene(singleScene);
        });

        Button btnCompete = new Button("Component Play");
        btnCompete.setOnAction(e ->  {
            CompeteBoard competeBoard = new CompeteBoard(menuScene, COMPETE_LAYOUT_X, COMPETE_LAYOUT_Y,
                    COMPETE_SPECIAL_LAYOUT_X, COMPETE_SPECIAL_LAYOUT_Y,
                    COMPETE_CHOICE_LAYOUT_X, COMPETE_CHOICE_LAYOUT_Y,
                    COMPETE_FIT_HEIGHT, COMPETE_FIT_WIDTH);
            competeScene = competeBoard.getScene();
            mainWindow.setScene(competeScene);
        });

        VBox hb = new VBox();
        hb.getChildren().addAll(title, btnStrView, btnSingle, btnCompete);
        hb.setSpacing(50);
        hb.setLayoutX(400);
        hb.setLayoutY(250);
        hb.setAlignment(Pos.CENTER);
        menuControls.getChildren().add(hb);
        menuRoot.getChildren().add(menuControls);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        primaryStage.setTitle("StepsGame Game");

        makeControls();

        menuScene.setFill(Color.LIGHTBLUE);
        primaryStage.setScene(menuScene);
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgramAlert();
        });
        primaryStage.show();
    }

    /**
     * This method will be called when the main window receives a request on close the window or stop the program.
     * A prompt or an alert window will pop out to confirm the exit request.
     * This idea came from a video: https://www.youtube.com/watch?v=SpL3EToqaXA.
     *
     * @author Wyman
     */
    private void closeProgramAlert() {
        Stage alertWin = new Stage();
        alertWin.setTitle("Confirm Exit");
        alertWin.initModality(Modality.APPLICATION_MODAL);

        // Setting for label.
        Label prompt = new Label("Do you want to exit the game?");
        prompt.setPadding(new Insets(20, 20, 20, 20));
        prompt.setAlignment(Pos.TOP_CENTER);

        // Setting for "yes" button. Close all windows.
        Button yes = new Button("Yes");
        yes.setAlignment(Pos.BOTTOM_LEFT);
        yes.setOnAction(e -> {
            alertWin.close();
            mainWindow.close();
        });

        // Setting for "cancel" button. Close the current window and return to previous window.
        Button cancel = new Button("Cancel");
        cancel.setAlignment(Pos.BOTTOM_RIGHT);
        cancel.setOnAction(e -> alertWin.close());

        // HBox contains two buttons horizontally.
        HBox hb = new HBox();
        hb.setAlignment(Pos.BOTTOM_CENTER);
        hb.getChildren().addAll(yes, cancel);

        // VBox contains the label and HBox vertically.
        VBox vb = new VBox();
        vb.setPadding(new Insets(10, 10, 20, 20));
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(prompt, hb);

        Scene scene = new Scene(vb, 400, 150);
        alertWin.setScene(scene);
        alertWin.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
