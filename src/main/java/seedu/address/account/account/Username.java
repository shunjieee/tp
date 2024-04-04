package seedu.address.account.account;

/**
 * Represents a username for an Account.
 * Each Username is associated with a username string.
 */
public class Username {
    public static final String MESSAGE_CONSTRAINTS = "Usernames should be alphanumeric and between 4 to 10 characters";
    private String username;

    /**
     * Constructs a Username instance with the specified username.
     *
     * @param username The username string.
     * @throws IllegalArgumentException If the username is invalid.
     */
    public Username(String username) {
        if (!isValidUsername(username)) {
            throw new IllegalArgumentException("Invalid username");
        }
        this.username = username;
    }

    /**
     * Returns the username.
     *
     * @return The username string.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks if the given username is valid.
     * A valid username is between 4 and 10 alphanumeric characters.
     *
     * @param username The username string to be checked.
     * @return True if the username is valid, false otherwise.
     */
    public static boolean isValidUsername(String username) {
        return username != null && username.matches("[a-zA-Z0-9]{4,10}");
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Username)) {
            return false;
        }

        Username otherUsername = (Username) obj;
        return otherUsername.getUsername().equals(username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
