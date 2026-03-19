package seedu.address.model.location;

/**
 * Represents a PostalCode in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPostalCode(String)}
 */
public class PostalCode {

    /**
     * Message shown when postal code constraints are violated.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Postal code should be alphanumeric.";

    /**
     * The postal code value.
     */
    public final String value;

    /**
     * Constructs a {@code PostalCode}.
     *
     * @param postalCode A valid postal code.
     */
    public PostalCode(String postalCode) {
        value = postalCode;
    }

    /**
     * Returns true if a given string is a valid postal code.
     *
     * @param test The string to test.
     * @return true if valid, false otherwise.
     */
    public static boolean isValidPostalCode(String test) {
        return test.matches("[a-zA-Z0-9]+");
    }

    /**
     * Returns the string representation of the postal code.
     *
     * @return postal code value.
     */
    @Override
    public String toString() {
        return value;
    }
}
