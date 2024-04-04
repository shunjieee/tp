package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("/name ");
    public static final Prefix PREFIX_PHONE = new Prefix("/hp ");
    public static final Prefix PREFIX_ID = new Prefix("/id ");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_PATH = new Prefix("/filename");

    public static final Prefix PREFIX_USERNAME = new Prefix("/u");
    public static final Prefix PREFIX_PASSWORD = new Prefix("/p");

}
