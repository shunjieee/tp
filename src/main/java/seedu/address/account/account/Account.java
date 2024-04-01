package seedu.address.account.account;

public class Account {
    private Username username;
    private String passwordHash;
    private SecretQuestion secretQuestion;

    public Account(Username username, String passwordHash, SecretQuestion secretQuestion) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.secretQuestion = secretQuestion;
    }

    public Username getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public SecretQuestion getSecretQuestion() {
        return secretQuestion;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setSecretQuestion(SecretQuestion secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public boolean checkPassword(String passwordHash) {
        return this.passwordHash.equals(passwordHash);
    }

    public boolean checkSecretQuestion(SecretQuestion secretQuestion) {
        return this.secretQuestion.equals(secretQuestion);
    }

    public boolean checkUsername(Username username) {
        return this.username.equals(username);
    }
}