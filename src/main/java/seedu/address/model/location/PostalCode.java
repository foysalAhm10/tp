package seedu.address.model.location;

public class PostalCode {
    public static final String MESSAGE_CONSTRAINTS =
            "Postal code should be alphanumeric.";

    public final String value;

    public PostalCode(String postalCode) {
        value = postalCode;
    }

    public static boolean isValidPostalCode(String test) {
        return test.matches("[a-zA-Z0-9]+");
    }

    @Override
    public String toString() {
        return value;
    }
}
