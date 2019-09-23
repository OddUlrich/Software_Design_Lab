package comp1110.lab7;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Fractals extends Application {
    private StackPane root = new StackPane();
    private GridPane pixelMap;
    private static final int PIXEL_SIZE = 3;
    private static final int X_PIXELS = 300;
    private static final int Y_PIXELS = 300;
    private static final int LOOP_FRAME_LENGTH = 3000;
    private Pixel[][] map = new Pixel[X_PIXELS][Y_PIXELS];
    private static final Map MAP_1, MAP_2, MAP_3;
    private Timeline animation;

    static { //Maps 1 -> 3 create the sierpinski triangle
        MAP_1 = new Map(0.5, 0.0, 0.0, 0.5, 0.0, 0.0);
        MAP_2 = new Map(0.5, 0.0, 0.0, 0.5, 0.0, X_PIXELS / 2.0);
        MAP_3 = new Map(0.5, 0.0, 0.0, 0.5, X_PIXELS / 2.0, X_PIXELS / 4.0);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // JavaFX setup
        primaryStage.setTitle("Fractal Visualiser!");
        primaryStage.setScene(new Scene(root, 1000, 1000));

        primaryStage.show();

        // Establishing the grid pane
        establishGrid();

        // Initialising our pixel map
        initialiseMap();

        // Initialising our state holder array for the animation loop, default to not mapped
        State[][] stateOut = new State[X_PIXELS][Y_PIXELS];
        for (int x = 0; x < X_PIXELS; x++) {
            for (int y = 0; y < Y_PIXELS; y++) {
                stateOut[x][y] = State.INACTIVE;
            }
        }

        // Create an animation which makes one application of the fractal transformation
        // each frame.
        animation = new Timeline(new KeyFrame(Duration.millis(LOOP_FRAME_LENGTH),
                ae -> {

                    // Reset the stateOut array to INACTIVE - unless a point is mapped to in an iteration,
                    // it should be inactive.
                    for (int x = 0; x < X_PIXELS; x++) {
                        for (int y = 0; y < Y_PIXELS; y++) {
                            stateOut[x][y] = State.INACTIVE;
                        }
                    }

                    // TODO 6 MISSING CODE SECTION
                    /*
                     * Insert a section of code here which:
                     *  - loops through all the positions of the grid
                     *  - if that position in 'map' is active (use 'isActive()') then apply each of the maps
                     *    MAP_1 -> MAP_3 for this position (use 'applyMap') and store these resulting points
                     *   to variables and,
                     *  - for each of the above resulting points, if it is within the bounds (use 'withinBounds'),
                     *    then set this position in 'stateOut' to ACTIVE.
                     * */
                    for (int x = 0; x < X_PIXELS; x++) {
                        for (int y = 0; y < Y_PIXELS; y++) {
                            if (map[x][y].isActive()) {
                                Point p1 = MAP_1.applyMap(x, y);
                                if (withinBounds(p1.x, p1.y)) {
                                    stateOut[p1.x][p1.y] = State.ACTIVE;
                                }
                                Point p2 = MAP_2.applyMap(x, y);
                                if (withinBounds(p2.x, p2.y)) {
                                    stateOut[p2.x][p2.y] = State.ACTIVE;
                                }
                                Point p3 = MAP_3.applyMap(x, y);
                                if (withinBounds(p3.x, p3.y)) {
                                    stateOut[p3.x][p3.y] = State.ACTIVE;
                                }
                            }
                        }
                    }

                    /*
                     * Afterwards, we apply this new state map over our existing 'map'
                     * */
                    for (int x = 0; x < X_PIXELS; x++) {
                        for (int y = 0; y < Y_PIXELS; y++) {
                            State s = stateOut[x][y];
                            if (s == State.ACTIVE) {
                                map[x][y].activate();
                            } else {
                                map[x][y].deactivate();

                            }
                        }
                    }
                }));

        // Begin this animation
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    // Establishes the gridpane with X_PIXELS number of columns and
    // Y_PIXELS number of rows.
    private void establishGrid() {
        pixelMap = new GridPane();
        for (int i = 0; i < Y_PIXELS; i++) {
            RowConstraints row = new RowConstraints(PIXEL_SIZE);
            pixelMap.getRowConstraints().add(row);
        }
        for (int i = 0; i < X_PIXELS; i++) {
            ColumnConstraints row = new ColumnConstraints(PIXEL_SIZE);
            pixelMap.getColumnConstraints().add(row);
        }
        root.getChildren().add(pixelMap);
    }

    // Creates pixels at each position on the grid and adds these
    // pixels to 'map'.
    private void initialiseMap() {
        for (int x = 0; x < X_PIXELS; x++) {
            for (int y = 0; y < Y_PIXELS; y++) {
                Pixel p = new Pixel(PIXEL_SIZE, State.ACTIVE);
                map[x][y] = p;
                pixelMap.add(p, x, y);
            }
        }
    }

    private boolean withinBounds(int x, int y) {
        return (x >= 0 && x < X_PIXELS && y >= 0 && y < Y_PIXELS);
    }

}
