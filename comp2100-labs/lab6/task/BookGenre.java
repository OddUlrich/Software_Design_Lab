import java.io.Serializable;

/**
 * Different kinds of genres that a Book can be.
 */
public enum BookGenre implements Serializable {
    FICTION_ACTION,
    FICTION_COMEDY,
    FICTION_FANTASY,
    NON_FICTION;

    /**
     * Displays this genre as a human-readable name.
     *
     * @return The name of this genre type.
     */
    public String display() {
        switch (this) {
            case FICTION_ACTION:
                return "Action (Fiction)";
            case FICTION_COMEDY:
                return "Comedy (Fiction)";
            case FICTION_FANTASY:
                return "Fantasy (Fiction)";
            case NON_FICTION:
                return "Non-Fiction";
            default:
                // Fallthrough catch-all
                return null;
        }
    }
}
