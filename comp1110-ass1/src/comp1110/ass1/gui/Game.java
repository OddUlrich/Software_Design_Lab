package comp1110.ass1.gui;

import comp1110.ass1.Hex;
import comp1110.ass1.IQStars;
import comp1110.ass1.Piece;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a JavaFX application that gives a graphical user interface (GUI) to the
 * simple IQStars game.
 * <p>
 * The tasks set for assignment one do NOT require you to refer to this class, so...
 * <p>
 * YOU MAY IGNORE THE CODE HERE ENTIRELY
 * <p>
 * ...while you do assignment one.
 * <p>
 * However, the class serves as a working example of a number of JavaFX concepts
 * that you may need later in the semester, so you may find this code helpful
 * later in the semester.
 * <p>
 * Among other things, the class demonstrates:
 * - Using inner classes that subclass standard JavaFX classes such as ImageView
 * - Using JavaFX groups to control properties such as visibility and lifetime of
 * a collection of objects
 * - Using opacity/transparency
 * - Using mouse events to implement a draggable object
 * - Making dropped objects snap to legal destinations
 * - Using a clickable button with an associated event
 * - Using a slider for user-input
 * - Using keyboard events to implement toggles controlled by the player
 * - Using bitmap images (public domain, CC0)
 * - Using an mp3 audio track (public domain, CC0)
 * - Using IllegalArgumentExceptions to check for and flag errors
 */
public class Game extends Application {

    /* board layout */
    private static final double HEX_HEIGHT = 65;
    private static final double HEX_WIDTH = (int) (HEX_HEIGHT * (Math.sqrt(3) / 2));

    private static final int MARGIN_X = (int) HEX_HEIGHT;
    private static final int BOARD_X = (int) HEX_HEIGHT * 4;
    private static final int MARGIN_Y = (int) (HEX_HEIGHT * 0.5);
    private static final int BOARD_Y = MARGIN_Y;
    private static final int BOARD_WIDTH = Hex.NUM_COLS * (int) HEX_HEIGHT;
    private static final int GAME_WIDTH = 2 * BOARD_X + BOARD_WIDTH;
    private static final int BOARD_HEIGHT = (int) (Hex.NUM_ROWS * HEX_HEIGHT);
    private static final int PIECE_AREA_HEIGHT = (int) (2 * Piece.MAX_PIECE_WIDTH * HEX_HEIGHT);
    private static final int GAME_HEIGHT = 2 * MARGIN_Y + BOARD_HEIGHT + PIECE_AREA_HEIGHT;

    /* color the underlying board */
    private static final Paint SUBBOARD_FILL = Color.DARKGREY;
    private static final Paint SUBBOARD_STROKE = Color.GREY;

    /* where to find media assets */
    private static final String URI_BASE = "assets/";

    /* Loop in public domain CC 0 http://www.freesound.org/people/oceanictrancer/sounds/211684/ */
    private static final String LOOP_URI = Game.class.getResource(URI_BASE + "211684__oceanictrancer__classic-house-loop-128-bpm.wav").toString();
    public static final int PIECES_PER_HOME_ROW = 4;
    private AudioClip loop;

    /* game variables */
    private boolean loopPlaying = false;

    /* node groups */
    private final Group root = new Group();
    private final Group solution = new Group();
    private final Group board = new Group();
    private final Group controls = new Group();
    private final Group pieces = new Group();

    /* the difficulty slider */
    private final Slider difficulty = new Slider();

    /* message on completion */
    private final Text completionText = new Text("Well done!");

    /* the underlying IQStars game */
    IQStars iqStars;

    String[] piecePlacements = new String[Piece.values().length];   //  all off screen to begin with

    class Star extends Polygon {
        double mouseX, mouseY;      // the last known mouse positions (used when dragging)
        double startX, startY;
        FXPiece piece;

        Star(double startX, double startY, Color fillColor, FXPiece piece) {
            this.startX = startX;
            this.startY = startY;
            setLayoutX(startX);
            setLayoutY(startY);
            this.piece = piece;

            double radius = HEX_HEIGHT * 3 / 5;

            addPointsForStar(getPoints(), radius);

            // create random Color and set as newStar's fill
            setStroke(fillColor);
            setStrokeWidth(6);
            setStrokeType(StrokeType.INSIDE);
            setFill(Color.WHITE);

            if (piece instanceof DraggableFXPiece) {
                DraggableFXPiece draggable = (DraggableFXPiece) piece;
                setOnMousePressed(event -> {      // mouse press indicates begin of drag
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();
                });

                setOnMouseDragged(event -> {      // mouse is being dragged
                    draggable.toFront();
                    double movementX = event.getSceneX() - mouseX;
                    double movementY = event.getSceneY() - mouseY;
                    draggable.drag(movementX, movementY);
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();
                });

                setOnMouseReleased(event -> {     // drag is complete
                    if (draggable.onBoard()) {
                        draggable.setPosition();
                        String placementString = getPlacementString();
                        if (IQStars.isValidPlacement(placementString)) {
                            // place piece
                            draggable.snapToGrid();
                            if (IQStars.fixOrientations(placementString) != null && IQStars.fixOrientations(placementString).equals(iqStars.getSolution())) {
                                showCompletion();
                            }
                        } else {
                            piecePlacements[piece.piece.ordinal()] = IQStars.NOT_PLACED;
                            draggable.snapToHome();
                        }
                    } else {
                        draggable.snapToHome();
                    }
                });

                /* event handlers */
                setOnScroll(event -> {            // scroll to change orientation
                    draggable.rotate();
                    if (draggable.onBoard()) {
                        draggable.setPosition();
                        String placementString = getPlacementString();
                        if (IQStars.isValidPlacement(placementString)) {
                            // place piece
                            draggable.snapToGrid();
                        } else {
                            piecePlacements[draggable.piece.ordinal()] = IQStars.NOT_PLACED;
                            draggable.snapToHome();
                        }
                    } else {
                        draggable.snapToHome();
                    }
                    event.consume();
                });
            }
        }
    }

    class FXPiece extends Group {
        final Piece piece;
        int col;
        int row;
        int rotation;
        Rotate rotate;
        List<Star> stars = new ArrayList<>();
        boolean invisible = false;

        FXPiece(char id) {
            if (!(id >= 'A' && id <= 'L')) {
                throw new IllegalArgumentException("Bad piece id: '" + id + "'");
            }
            piece = Piece.valueOf(String.valueOf(id));

            Color pieceColor = getColorForPiece(piece);

            for (Hex hex : piece.shape) {
                double xOffset = hex.row % 2 == 0 ? 0 : 0.5;
                // distance between rows is 3/4 HEX_HEIGHT; distance between columns is HEX_WIDTH
                Star star = new Star((xOffset + hex.col) * HEX_WIDTH, hex.row * 3.0 / 4 * HEX_HEIGHT, pieceColor, this);
                stars.add(star);
                pieces.getChildren().add(star);
            }

            rotate = new Rotate(); // Pivot X Top-Left corner
            rotate.setPivotX(0);
            rotate.setPivotY(0);
            getTransforms().add(rotate);
        }

        private Color getColorForPiece(Piece piece) {
            Color color;
            switch (piece) {
                case A:
                    color = Color.PURPLE;
                    break;
                case B:
                    color = Color.ORANGE;
                    break;
                case C:
                    color = Color.MAGENTA;
                    break;
                case D:
                    color = Color.RED;
                    break;
                case E:
                    color = Color.GREEN;
                    break;
                case F:
                    color = Color.YELLOW;
                    break;
                case G:
                    color = Color.BLUE;
                    break;
                default:
                    color = Color.GREY;
            }
            return color;
        }

        /**
         * Construct a piece at a particular place on the
         * board at a given orientation.
         *
         * @param placementString a four-character piece placement string
         */
        FXPiece(String placementString) {
            this(placementString.charAt(0));
            char hexChar = placementString.charAt(1);
            Hex hex = new Hex(hexChar);
            this.row = hex.row;
            this.col = hex.col;
            char rotationChar = placementString.charAt(2);
            rotation = rotationChar - 'A';
            updateRotation();
            snapToGrid();
        }

        protected void updateRotation() {
            rotate.setAngle((rotation % 6) * 60);
            for (Star star : stars) {
                double radius = Math.hypot(star.startX, star.startY);
                double angle = Math.atan2(star.startY, star.startX);
                double newAngle = angle + Math.toRadians(rotate.getAngle());
                double newX = Math.cos(newAngle) * radius;
                double newY = Math.sin(newAngle) * radius;
                star.setLayoutX(newX);
                star.setLayoutY(newY);
            }
        }

        /**
         * Snap the piece to the nearest grid position (if it is over the grid)
         */
        protected void snapToGrid() {
            double xOffset = row % 2 == 0 ? 0 : 0.5;
            setLayoutX(BOARD_X + (xOffset + col) * HEX_WIDTH);
            setLayoutY(BOARD_Y + ((row * 3.0 / 4.0)) * HEX_HEIGHT);
            for (Star star : stars) {
                star.setTranslateX(getLayoutX());
                star.setTranslateY(getLayoutY());
                star.setOpacity(1.0);
            }
        }

        protected void toggleInvisible() {
            double opacity;
            if (invisible) {
                invisible = false;
                opacity = 1.0;
            } else {
                invisible = true;
                opacity = 0.0;
            }
            for (Star star : stars) {
                star.setOpacity(opacity);
            }
        }
    }

    /**
     * This class extends FXPatch with the capacity for it to be dragged and dropped,
     * and snap-to-grid.
     */
    class DraggableFXPiece extends FXPiece {
        double homeX, homeY;         // the position in the window where the piece should be when not on the board

        /**
         * Construct a draggable piece
         *
         * @param id The piece identifier ('A' - 'L')
         */
        DraggableFXPiece(char id) {
            super(id);

            int index = id - 'A';
            int homeCol = (index % PIECES_PER_HOME_ROW);
            this.homeX = homeCol * HEX_HEIGHT * (Piece.MAX_PIECE_WIDTH + 0.4) + HEX_HEIGHT;
            int homeRow = index / PIECES_PER_HOME_ROW;
            this.homeY = BOARD_HEIGHT + MARGIN_Y + HEX_HEIGHT * 0.5 + Piece.MAX_PIECE_WIDTH * HEX_HEIGHT * homeRow;

            snapToHome();
        }

        protected void drag(double movementX, double movementY) {
            setLayoutX(getLayoutX() + movementX);
            setLayoutY(getLayoutY() + movementY);
            for (Star star : stars) {
                star.setTranslateX(getLayoutX());
                star.setTranslateY(getLayoutY());
                star.setOpacity(0.5);
                star.toFront();
            }
        }

        /**
         * @return true if the piece is on the board
         */
        private boolean onBoard() {
            return getLayoutX() > (BOARD_X - HEX_WIDTH) && (getLayoutX() < (BOARD_X + BOARD_WIDTH))
                    && getLayoutY() > (BOARD_Y - HEX_HEIGHT) && (getLayoutY() < (BOARD_Y + BOARD_HEIGHT));
        }

        /**
         * Snap the piece to its home position (if it is not on the grid)
         */
        private void snapToHome() {
            setLayoutX(homeX);
            setLayoutY(homeY);
            for (Star star : stars) {
                star.setTranslateX(getLayoutX());
                star.setTranslateY(getLayoutY());
                star.setOpacity(1.0);
            }
            setRotate(0);
            piecePlacements[piece.ordinal()] = IQStars.NOT_PLACED;
            setOpacity(1.0);
        }

        /**
         * Rotate the piece by 60 degrees.
         */
        private void rotate() {
            rotation = (rotation + 1) % 6;
            updateRotation();
        }

        /**
         * Determine the grid-position of the origin of the piece (0 .. 12)
         * or -1 if it is off the grid, taking into account its rotation.
         */
        private void setPosition() {
            row = (int) ((getLayoutY() + 0.5 * HEX_HEIGHT - BOARD_Y) / (HEX_HEIGHT * 3.0 / 4.0));
            double xOffset = row % 2 == 0 ? 0 : 0.5;
            col = (int) ((getLayoutX() + (0.5 - xOffset) * HEX_WIDTH - BOARD_X) / HEX_WIDTH);
            Hex hex = new Hex(col, row);
            piecePlacements[piece.ordinal()] = String.valueOf(new char[]{
                    piece.getId(),
                    hex.getIndex(),
                    (char) ('A' + rotation)});
        }


        /**
         * @return the piece placement represented as a string
         */
        public String toString() {
            return "" + piecePlacements[piece.ordinal()];
        }
    }

    /**
     * Create a star of the given radius
     */
    private static void addPointsForStar(ObservableList<Double> points, double radius) {
        // start at rightmost corner, which is rotated 10 degrees
        double bearing = -1; // in 36ths of a circle, or PI/18 radians
        for (int i = 0; i < 6; i++) {
            // point
            double x = Math.cos(Math.PI / 18 * bearing) * radius;
            double y = Math.sin(Math.PI / 18 * bearing) * radius;
            points.add(x);
            points.add(y);
            bearing += 3;
            // inner corner
            x = Math.cos(Math.PI / 18 * bearing) * radius / 2;
            y = Math.sin(Math.PI / 18 * bearing) * radius / 2;
            points.add(x);
            points.add(y);
            bearing += 3;
        }
    }

    private String getPlacementString() {
        StringBuilder sb = new StringBuilder();
        for (String piecePlacementString : piecePlacements) {
            sb.append(piecePlacementString);
        }
        return sb.toString();
    }


    /**
     * Set up event handlers for the main game
     *
     * @param scene The Scene used by the game.
     */
    private void setUpHandlers(Scene scene) {
        /* create handlers for key press and release events */
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.M) {
                toggleSoundLoop();
                event.consume();
            } else if (event.getCode() == KeyCode.Q) {
                Platform.exit();
                event.consume();
            } else if (event.getCode() == KeyCode.SLASH) {
                toggleSolutionVisibility();
                event.consume();
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.SLASH) {
                toggleSolutionVisibility();
                event.consume();
            }
        });
    }

    private void toggleSolutionVisibility() {
        for (Node node : solution.getChildren()) {
            if (node instanceof FXPiece) {
                FXPiece piece = (FXPiece) (node);
                piece.toggleInvisible();
            }
        }
    }


    /**
     * Set up the sound loop (to play when the 'M' key is pressed)
     */
    private void setUpSoundLoop() {
        try {
            loop = new AudioClip(LOOP_URI);
            loop.setCycleCount(AudioClip.INDEFINITE);
        } catch (Exception e) {
            System.err.println(":-( something bad happened (" + LOOP_URI + "): " + e);
        }
    }


    /**
     * Turn the sound loop on or off
     */
    private void toggleSoundLoop() {
        if (loopPlaying)
            loop.stop();
        else
            loop.play();
        loopPlaying = !loopPlaying;
    }


    /**
     * Set up the group that represents the solution (and make it transparent)
     *
     * @param solution The solution string.
     */
    private void makeSolution(String solution) {
        this.solution.getChildren().clear();
        if (solution == null) return;

        if (solution.length() != Piece.values().length * 3) {
            throw new IllegalArgumentException("Solution incorrect length: " + solution);
        }
        for (int i = 0; i < solution.length(); i += 3) {
            FXPiece piece = new FXPiece(solution.substring(i, i + 3));
            piece.toggleInvisible();
            this.solution.getChildren().add(piece);
        }
    }


    /**
     * Set up the group that represents the spaces that make the board
     */
    private void makeBoard() {
        board.setLayoutX(BOARD_X + MARGIN_X);
        board.setLayoutY(BOARD_Y + MARGIN_Y);
        board.getChildren().clear();
        Rectangle background = new Rectangle(Hex.NUM_COLS * HEX_WIDTH, (0.25 + Hex.NUM_ROWS * 3.0 / 4) * HEX_HEIGHT);
        background.setLayoutX(-0.5 * HEX_WIDTH);
        background.setLayoutY(-0.5 * HEX_HEIGHT);
        background.setFill(SUBBOARD_FILL);
        background.setStroke(SUBBOARD_FILL);
        background.setStrokeWidth(HEX_WIDTH / 2);
        background.setStrokeType(StrokeType.OUTSIDE);
        board.getChildren().add(background);

        for (int row = 0; row < Hex.NUM_ROWS; row++) {
            int numCols = row % 2 == 0 ? Hex.NUM_COLS : Hex.NUM_COLS - 1;
            double xOffset = row % 2 == 0 ? 0 : 0.5 * HEX_WIDTH;
            for (int col = 0; col < numCols; col++) {
                // each hex is marked with a small star
                Polygon smallStar = new Polygon();
                smallStar.setFill(Color.BLACK);
                smallStar.setStroke(SUBBOARD_STROKE);
                addPointsForStar(smallStar.getPoints(), HEX_WIDTH / 3);
                smallStar.setLayoutX(xOffset + col * HEX_WIDTH);
                smallStar.setLayoutY(row * 3.0 / 4.0 * HEX_HEIGHT);
                board.getChildren().add(smallStar);
            }
        }
        board.toBack();
    }


    /**
     * Set up each of the twelve pieces
     */
    private void makePieces(String objective) {
        pieces.getChildren().clear();
        for (Piece piece : Piece.values()) {
            if (iqStars.isPieceFixed(piece.getId())) {
                int offset = 0;
                while (objective.charAt(offset) != piece.getId()) offset += 3;
                String placement = objective.substring(offset, offset + 3);
                piecePlacements[piece.ordinal()] = placement;
                pieces.getChildren().add(new FXPiece(placement));
            } else {
                pieces.getChildren().add(new DraggableFXPiece(piece.getId()));
                piecePlacements[piece.ordinal()] = IQStars.NOT_PLACED;
            }
        }
        pieces.setLayoutX(MARGIN_X);
        pieces.setLayoutY(MARGIN_Y);
    }

    /**
     * Put all of the pieces back in their home position
     */
    private void resetPieces() {
        pieces.toFront();
        for (Node n : pieces.getChildren()) {
            if (n instanceof DraggableFXPiece) {
                ((DraggableFXPiece) n).snapToHome();
            }
        }
    }


    /**
     * Create the controls that allow the game to be restarted and the difficulty
     * level set.
     */
    private void makeControls() {
        Button button = new Button("Restart");
        button.setLayoutX(GAME_WIDTH - 150);
        button.setLayoutY(GAME_HEIGHT - 45);
        button.setOnAction(e -> newGame());
        controls.getChildren().add(button);

        difficulty.setMin(0);
        difficulty.setMax(10);
        difficulty.setValue(1);
        difficulty.setShowTickLabels(true);
        difficulty.setShowTickMarks(true);
        difficulty.setMajorTickUnit(5);
        difficulty.setMinorTickCount(1);
        difficulty.setSnapToTicks(true);

        difficulty.setLayoutX(GAME_WIDTH - 180);
        difficulty.setLayoutY(GAME_HEIGHT - 85);
        controls.getChildren().add(difficulty);

        final Label difficultyCaption = new Label("Difficulty:");
        difficultyCaption.setTextFill(Color.GREY);
        difficultyCaption.setLayoutX(GAME_WIDTH - 240);
        difficultyCaption.setLayoutY(GAME_HEIGHT - 85);
        controls.getChildren().add(difficultyCaption);
    }


    /**
     * Create the message to be displayed when the player completes the puzzle.
     */
    private void makeCompletion() {
        DropShadow ds = new DropShadow();
        ds.setOffsetY(4.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        completionText.setFill(Color.RED);
        completionText.setEffect(ds);
        completionText.setCache(true);
        completionText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 80));
        completionText.setLayoutX(GAME_WIDTH / 2.0 - 200);
        completionText.setLayoutY(350);
        completionText.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().add(completionText);
    }

    /**
     * Show the completion message
     */
    private void showCompletion() {
        completionText.toFront();
        completionText.setOpacity(1);
    }

    /**
     * IQStars the completion message
     */
    private void hideCompletion() {
        completionText.toBack();
        completionText.setOpacity(0);
    }


    /**
     * Start a new game, resetting everything as necessary
     */
    private void newGame() {
        try {
            hideCompletion();
            iqStars = new IQStars(difficulty.getValue());
            makePieces(iqStars.getObjective());
            makeSolution(iqStars.getSolution());
        } catch (IllegalArgumentException e) {
            System.err.println("Uh oh. " + e);
            e.printStackTrace();
            Platform.exit();
        }
    }


    /**
     * The entry point for JavaFX.  This method gets called when JavaFX starts
     * The key setup is all done by this method.
     *
     * @param primaryStage The stage (window) in which the game occurs.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("IQ Stars");
        Scene scene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);
        root.getChildren().add(pieces);
        root.getChildren().add(board);
        root.getChildren().add(solution);
        root.getChildren().add(controls);

        setUpHandlers(scene);
        setUpSoundLoop();
        makeBoard();
        makeControls();
        makeCompletion();

        newGame();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
