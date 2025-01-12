package mastermind.core.solvers;

/**
 * Exception thrown when the hints provided to a solver are invalid.
 */
public class InvalidHintsException extends IllegalArgumentException {
    /**
     * Constructs a new InvalidHintsException with the specified detail message.
     *
     * @param s the detail message
     */
    public InvalidHintsException(final String s) {
        super(s);
    }
}
