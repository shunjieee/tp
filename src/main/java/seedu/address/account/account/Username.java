package seedu.address.account.account;

public class Username {
    private String username;

    public Username(String username) {
        if (!isValidUsername(username)) {
            throw new IllegalArgumentException("Invalid username");
        }
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    private boolean isValidUsername(String username) {
        return username != null && username.matches("[a-zA-Z0-9]{4,10}");
    }
}