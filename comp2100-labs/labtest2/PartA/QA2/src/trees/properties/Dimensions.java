package trees.properties;

import trees.Property;

/**
 * Stores the dimensions of a tree.
 */
public class Dimensions extends Property {
    private final int diameter;
    private final int height;

    /**
     * Creates a new Dimensions object.
     *
     * @param diameter The diameter of a tree.
     * @param height The height of a tree.
     */
    public Dimensions(int diameter, int height) {
        this.diameter = diameter;
        this.height = height;
    }

    /**
     * Returns the diameter of this tree in meters.
     *
     * @return A integer measurement in meters.
     */
    public int getDiameter() {
        return diameter;
    }

    /**
     * Returns the height of this tree in meters.
     *
     * @return A integer measurement in meters.
     */
    public int getHeight() {
        return height;
    }

    @Override
    public String getName() {
        return "dimensions";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Dimensions &&
                ((Dimensions) o).getDiameter() == diameter &&
                ((Dimensions) o).getHeight() == height;
    }

    // Note: Not useful for you when generating XML; just to see if your answer looks right!
    @Override
    public String toString() {
        return "Dimensions{" +
                "diameter=" + diameter +
                ", height=" + height +
                '}';
    }
}
