package comp1110.lab6;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Board extends Application {
    static double WINDOWS_LAYOUT_X = 600.0;
    static double WINDOWS_LAYOUT_Y = 519.0;
    static double CENTER_X = WINDOWS_LAYOUT_X / 2.0;
    static double CENTER_Y = WINDOWS_LAYOUT_Y / 2.0;

    static double SIDE = 196;
    double delta_layout_Y = Math.sqrt(200*200 - (200/2)*(200/2));

    Stage window;

    class Triangle extends Polygon {
        Double[] pointArr = new Double[6];
        Triangle(double x, double y, double side) {
            double deltaY = Math.sqrt(side*side - (side/2)*(side/2)) / 2;
            pointArr[0] = x;
            pointArr[1] = y - deltaY;
            pointArr[2] = x + side / 2;
            pointArr[3] = y + deltaY;
            pointArr[4] = x - side / 2;
            pointArr[5] = y + deltaY;

            this.getPoints().addAll(pointArr);
        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Board");

        Group root = new Group();
        Scene scene = new Scene(root, WINDOWS_LAYOUT_X, WINDOWS_LAYOUT_Y);
        ArrayList<Triangle> triGroup = new ArrayList<>();

//        // Step 2.
//        Polygon triangle = new Polygon();
//        triangle.getPoints().addAll(new Double[]{
//                CENTER_X, CENTER_Y - 86.6,
//                CENTER_X + 100.0, CENTER_Y + 86.6,
//                CENTER_X - 100.0, CENTER_Y + 86.6
//        });
//        triangle.setFill(Color.LIGHTGREY);
//        root.getChildren().add(triangle);

//        // Step 3.
//        Triangle triangle = new Triangle(CENTER_X, CENTER_Y, SIDE);
//        triangle.setFill(Color.LIGHTGREY);
//        root.getChildren().add(triangle);


        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 5; col++){
                double x = CENTER_X + 100 * (col - 2);
                double y = CENTER_Y + delta_layout_Y * (row - 1);
                Triangle newTri = new Triangle(x, y, SIDE);
                newTri.setFill(Color.LIGHTGREY);

                if (col % 2 == 1) {
                    newTri.setRotate(180);
                }

                triGroup.add(newTri);
                root.getChildren().add(newTri);
            }
        }

        stage.setScene(scene);
        stage.show();
    }
}
