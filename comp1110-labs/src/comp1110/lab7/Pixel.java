package comp1110.lab7;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a 'pixel' in the fractal i.e. a square element of a given size
 * to be displayed on the screen.
 */
public class Pixel extends Rectangle {
    private Color color = null;
    private State state;


    // Complete the Pixel constructor so that the rectangle has sides 'size' and
    // state 'pixelState'. Set the color based on the 'pixelState'.
    // Make the rectangle fill color the same as 'color'. (Hint, try looking at
    // the documentation for the methods of the super class 'Rectangle' which
    // relate to fill).
    public Pixel(int size, State pixelState) {
        // TODO 2 Fix me
        super(size,size);
        this.state = pixelState;
        this.color = State.colorFromState(this.state);
        this.setFill(this.color);
    }

    // Complete this method, which sets the Pixel's state to INACTIVE, and
    // updates the Pixel's color and fill colour to WHITE
    public void deactivate() {
        // TODO 3 Fix me
        this.state = State.INACTIVE;
        this.color = State.colorFromState(this.state);
        this.setFill(this.color);
    }

    // Complete this method, which sets the Pixel's state to ACTIVE, and
    // updates the Pixel's color and fill colour to BLACK
    public void activate() {
        // TODO 4 Fix me
        this.state = State.ACTIVE;
        this.color = State.colorFromState(this.state);
        this.setFill(this.color);
    }

    // Return a boolean which states whether the Pixel is active (i.e. has state ACTIVE).
    public boolean isActive() {
        // TODO 5 Fix me
        return (this.state == State.ACTIVE);
    }

    public State getState() {
        return this.state;
    }

}