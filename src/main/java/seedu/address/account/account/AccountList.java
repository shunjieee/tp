package seedu.address.account.account;

import seedu.address.account.account.Account;

import java.util.HashMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class AccountList {
    private Map<Username, Account> accounts;

    public AccountList() {
        this.accounts = new HashMap<>();
    }

    /**
     * Adds a new account to the list. Returns true if the account was successfully added,
     * or false if an account with the same username already exists.
     */
    public boolean addAccount(Account account) {
        if (accounts.containsKey(account.getUsername())) {
            return false;
        }
        accounts.put(account.getUsername(), account);
        return true;
    }

    /**
     * Attempts to authenticate a user with the provided username and password.
     * Returns the Account object if authentication is successful, or null otherwise.
     */
    public Account authenticate(String username, String passwordHash) {
        Account account = accounts.get(username);
        if (account != null && account.getPasswordHash().equals(passwordHash)) {
            return account; // Authentication successful
        }
        return null; // Authentication failed
    }

    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
