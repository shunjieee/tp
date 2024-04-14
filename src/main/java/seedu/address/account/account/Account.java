package seedu.address.account.account;

/**
 * Represents an Account.
 * Each Account is associated with a username and a hashed password.
 */
public class Account {
    private final Username username;
    private final Password passwordHash;

    /**
     * Constructs an Account instance with the specified username and password hash.
     *
     * @param username The username associated with this account.
     * @param passwordHash The hashed password for this account.
     */
    public Account(Username username, Password passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    /**
     * Returns the username associated with this account.
     *
     * @return The username of this account.
     */
    public Username getUsername() {
        return username;
    }

    /**
     * Returns the hashed password of this account.
     *
     * @return The hashed password of this account.
     */
    public Password getPasswordHash() {
        return passwordHash;
    }

    /**
     * Returns a string representation of the account, including the username and hashed password.
     *
     * @return A string representation of the account.
     */
    @Override
    public String toString() {
        return "Username: " + username + " | Password Hashed: " + passwordHash;
    }
}
