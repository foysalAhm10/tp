package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_TAG_ADD = new Prefix("t+/");
    public static final Prefix PREFIX_TAG_REMOVE = new Prefix("t-/");
    public static final Prefix PREFIX_DATE_ADD = new Prefix("d+/");
    public static final Prefix PREFIX_DATE_REMOVE = new Prefix("d-/");

}
