package trees.properties;

import trees.Property;

/**
 * Stores a color that a tree is.
 */
public class Color extends Property {
    private final String color;

    /**
     * Creates a new Color property.
     *
     * @param color A color.
     */
    public Color(String color) {
        this.color = color;
    }

    /**
     * Returns a String description of the color of a tree.
     *
     * @return A human-readable color.
     */
    public String getColor() {
        return color;
    }

    @Override
    public String getName() {
        return "color";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Color && ((Color) o).getColor().equals(color);
    }

    // Note: Not useful for you when generating XML; just to see if your answer looks right!
    @Override
    public String toString() {
        return "Color{" +
                "color='" + color + '\'' +
                '}';
    }
}
