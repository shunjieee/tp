package seedu.address.account.account;

/**
 * Represents an Account.
 * Each Account is associated with a username and a hashed password.
 */
public class Account {
    private final Username username;
    private String passwordHash;

    /**
     * Constructs an Account instance with the specified username and password hash.
     *
     * @param username The username associated with this account.
     * @param passwordHash The hashed password for this account.
     */
    public Account(Username username, String passwordHash) {
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
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the hashed password for this account.
     *
     * @param passwordHash The new hashed password.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Checks if the provided password hash matches the account's password hash.
     *
     * @param passwordHash The password hash to check.
     * @return True if the provided password hash matches the account's password hash, false otherwise.
     */
    public boolean checkPassword(String passwordHash) {
        return this.passwordHash.equals(passwordHash);
    }

    /**
     * Checks if the provided username matches the account's username.
     *
     * @param username The username to check.
     * @return True if the provided username matches the account's username, false otherwise.
     */
    public boolean checkUsername(Username username) {
        return this.username.equals(username);
    }

    /**
     * Returns a string representation of the account, including the username and hashed password.
     *
     * @return A string representation of the account.
     */
    @Override
    public String toString() {
        return "Username: " + username + " | Password Hashed: "
                + passwordHash;
    }
}
