package comp1110.lab5;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

import static javafx.application.Application.launch;

public class Ant extends Application {

    private static final int RECTANGLE_WINDOW_SIZE = 200;
    private static final int CENTEN_POS = RECTANGLE_WINDOW_SIZE / 2;
    private static final int STEP_RANDOM_RANGE = 20;
    private static final int POINT_RADIUS = 3;

    private int lastPosX;
    private int lastPosY;

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, RECTANGLE_WINDOW_SIZE, RECTANGLE_WINDOW_SIZE);
        Random rand = new Random();
        Circle point = new Circle();

        stage.setTitle("Ant");
        stage.setScene(scene);

        point.setRadius(POINT_RADIUS);
        point.setCenterX(CENTEN_POS);
        point.setCenterY(CENTEN_POS);
        root.getChildren().add(point);

        lastPosX = CENTEN_POS;
        lastPosY = CENTEN_POS;

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),
                ae -> {
                    int xMove;
                    int yMove;
                    Circle newPoint = new Circle();

                    newPoint.setRadius(POINT_RADIUS);

                    xMove = (int) (rand.nextInt(STEP_RANDOM_RANGE + 1) - (STEP_RANDOM_RANGE + 1) / 2);
                    yMove = (int) (rand.nextInt(STEP_RANDOM_RANGE + 1) - (STEP_RANDOM_RANGE + 1) / 2);

                    if (lastPosX + xMove == 0 || lastPosX + xMove == RECTANGLE_WINDOW_SIZE
                        || lastPosY + yMove == 0 || lastPosY + yMove == RECTANGLE_WINDOW_SIZE) {
                        newPoint.setCenterX(CENTEN_POS);
                        newPoint.setCenterY(CENTEN_POS);
                        root.getChildren().clear();
                    } else {
                        newPoint.setCenterX(lastPosX + xMove);
                        newPoint.setCenterY(lastPosY + yMove);
                    }

                    lastPosX = (int) newPoint.getCenterX();
                    lastPosY = (int) newPoint.getCenterY();
                    root.getChildren().add(newPoint);
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

