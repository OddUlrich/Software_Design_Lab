package comp1110.ass2.gui;

import comp1110.ass2.RailroadInk;
import comp1110.ass2.Tile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * A simple viewer for tile placements in the Railroad Ink game.
 */

class StringViewer extends Layers {
    private int fitHeight;
    private int fitWidth;
    private TextField textField;

    StringViewer(Scene menuScene, int layoutX, int layoutY, int fitHeight, int fitWidth) {
        super(menuScene, layoutX, layoutY, fitHeight, fitWidth);
        this.fitHeight = fitHeight;
        this.fitWidth = fitWidth;

        makeStrBoardControl();
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeStrBoardControl() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(e -> {
            makePlacement(textField.getText());
            textField.clear();
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(300);
        hb.setLayoutY(VIEWER_HEIGHT - 50);

        controls.getChildren().add(hb);
    }

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    private void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer

        if (!RailroadInk.isBoardStringWellFormed(placement)) {
            invalidBoardStringAlert();
            return;
        }

        Node lineNode = board.getChildren().get(0);
        board.getChildren().clear();
        board.getChildren().add(0, lineNode);

        // Place tile imageView into the board pane.
        for (int idx = 0; idx < placement.length(); idx += 5) {
            Tile tile = new Tile(placement.substring(idx, idx + 5));
            String imgPath = URI_BASE + tile.name + ".png";

            // Create imgView into the board.
            Image image = new Image(Game.class.getResource(imgPath).toString());
            ImageView imgView = new ImageView(image);
            tile.setImgView(imgView);

            imgView.setFitHeight(fitHeight);
            imgView.setFitWidth(fitWidth);
            imgView.setPreserveRatio(true);
            tile.placeImgView();

            board.setConstraints(imgView, tile.getColPos(), tile.getRowPos());
            board.getChildren().add(imgView);
        }
    }

    /**
     * This method will be called when restart the game with an invalid board string.
     * A prompt or an alert window will pop out to warning the error.
     *
     * @author Wyman
     */
    private void invalidBoardStringAlert() {
        Stage alertWin = new Stage();
        alertWin.setTitle("Warning");
        alertWin.initModality(Modality.APPLICATION_MODAL);

        // Setting for label.
        Label prompt = new Label("Your board placement string is invalid.\n\n Please check your input.");
        prompt.setPadding(new Insets(20, 20, 20, 20));
        prompt.setAlignment(Pos.TOP_CENTER);

        // Setting for "yes" button. Close all windows.
        Button ok = new Button("OK");
        ok.setAlignment(Pos.BOTTOM_CENTER);
        ok.setOnAction(e -> alertWin.close());

        // VBox contains the label and HBox vertically.
        VBox vb = new VBox();
        vb.setPadding(new Insets(10, 10, 20, 20));
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(prompt, ok);

        Scene scene = new Scene(vb, 400, 150);
        alertWin.setScene(scene);
        alertWin.showAndWait();
    }
}
