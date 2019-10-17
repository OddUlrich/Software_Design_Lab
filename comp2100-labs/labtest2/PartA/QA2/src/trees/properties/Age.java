package trees.properties;

import trees.Property;

/**
 * Stores the age of a tree.
 */
public class Age extends Property {
    private final int age;

    /**
     * Creates a new Age property.
     *
     * @param age The age of this tree in years.
     */
    public Age(int age) {
        this.age = age;
    }

    /**
     * Returns how old this tree is.
     *
     * @return A integer count in years.
     */
    public int getAge() {
        return age;
    }

    @Override
    public String getName() {
        return "age";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Age && ((Age) o).getAge() == age;
    }

    // Note: Not useful for you when generating XML; just to see if your answer looks right!
    @Override
    public String toString() {
        return "Age{" +
                "age=" + age +
                '}';
    }
}
