package comp1110.lab7;

import javafx.scene.paint.Color;

public enum State {
    ACTIVE, INACTIVE;

    // Complete the method colorFromState which given a State s returns
    // the 'alive' color if the state is ACTIVE and the 'dead' colour if
    // the state is INACTIVE.
    static Color colorFromState(State s) {
        // TODO 1 Fix me
        if (s == ACTIVE) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }
}
