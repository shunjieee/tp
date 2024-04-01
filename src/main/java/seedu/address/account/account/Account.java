package seedu.address.account.account;

/**
 * Represents an Account.
 * Each Account is associated with a username, a hashed password, and a secret question.
 */
public class Account {
    private Username username;
    private String passwordHash;
    private SecretQuestion secretQuestion;

    /**
     * Constructs an Account instance with the specified username, password hash, and secret question.
     *
     * @param username The username associated with this account.
     * @param passwordHash The hashed password for this account.
     * @param secretQuestion The secret question for this account.
     */
    public Account(Username username, String passwordHash, SecretQuestion secretQuestion) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.secretQuestion = secretQuestion;
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
     * Returns the secret question associated with this account.
     *
     * @return The secret question of this account.
     */
    public SecretQuestion getSecretQuestion() {
        return secretQuestion;
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
     * Sets the secret question for this account.
     *
     * @param secretQuestion The new secret question.
     */
    public void setSecretQuestion(SecretQuestion secretQuestion) {
        this.secretQuestion = secretQuestion;
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
     * Checks if the provided secret question matches the account's secret question.
     *
     * @param secretQuestion The secret question to check.
     * @return True if the provided secret question matches the account's secret question, false otherwise.
     */
    public boolean checkSecretQuestion(SecretQuestion secretQuestion) {
        return this.secretQuestion.equals(secretQuestion);
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
     * Returns a string representation of the account, including the username, hashed password, and secret question.
     *
     * @return A string representation of the account.
     */
    @Override
    public String toString() {
        String question = secretQuestion.getQuestion();
        String answer = secretQuestion.getAnswer();
        return "Username: " + username + " | Password Hashed: "
                + passwordHash + " | Secret Question: " + question + " | Answer: " + answer;
    }
}
