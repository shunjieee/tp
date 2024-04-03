package seedu.address.account.account;

/**
 * Represents a password for an Account.
 * Each Password is associated with a hashed password string.
 */
public class Password {
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
}
