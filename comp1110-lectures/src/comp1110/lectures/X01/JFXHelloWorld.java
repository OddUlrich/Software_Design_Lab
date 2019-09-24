package comp1110.lectures.X01;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JFXHelloWorld extends Application {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello");

        Group root = new Group();
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);

        StackPane pane = new StackPane();
        Text hi = new Text("Hello world!");
        hi.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        hi.setFill(Color.RED);
        pane.getChildren().add(hi);

        root.getChildren().add(pane);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
