package seedu.address.account.account;

/**
 * Represents a password for an Account.
 * Each Password is associated with a hashed password string.
 */
public class Password {
    public static final String MESSAGE_CONSTRAINTS = "Passwords should be 6 to 20 "
            + "characters long and not contain spaces.";
    private String hashedPassword;

    /**
     * Constructs a Password instance with the specified hashed password.
     *
     * @param hashedPassword The hashed password string.
     */
    public Password(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    /**
     * Returns the hashed password.
     *
     * @return The hashed password string.
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Checks if the given password is valid.
     * A valid password is between 6 and 20 characters long and does not contain spaces.
     *
     * @param password The password string to be checked.
     * @return True if the password is valid, false otherwise.
     */
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6 && password.length() <= 20 && !password.contains(" ");
    }
}
