package trees;

import java.util.Arrays;
import java.util.List;

/**
 * Represents properties and the name of a physical tree sample.
 */
public class Tree {
    private final String kind;
    private final List<Property> properties;

    /**
     * Creates a new Tree with the specified attributes and properties.
     *
     * @param properties A list of the properties that this tree has.
     */
    public Tree(String kind, Property[] properties) {
        this.kind = kind;
        this.properties = Arrays.asList(properties);
    }

    /**
     * Returns the kind of Tree that this is.
     * @return A human readable description of a Tree.
     */
    public String getKind() {
        return kind;
    }

    /**
     * Returns a copy of all the properties in this tree.
     *
     * @return An Array of Properties
     */
    public Property[] getProperties() {
        return properties.toArray(new Property[0]);
    }

    /**
     * Checks to see if another Tree object is equal to this.
     *
     * @param o The other object.
     * @return If the other object was indeed equal.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Tree && ((Tree) o).kind.equals(kind) && properties.equals(((Tree) o).properties);
    }

    // Note: Not useful for you when generating XML; just to see if your answer looks right!
    @Override
    public String toString() {
        return "Tree{" +
                "kind='" + kind + '\'' +
                ", properties=" + properties +
                '}';
    }
}
