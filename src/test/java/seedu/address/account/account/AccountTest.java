package seedu.address.account.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AccountTest {
    private static final String TEST_USERNAME = "test";
    private static final String TEST_PASSWORD_HASH = "5f4dcc3b5aa765d61d8327deb882cf99";
    private static final String WRONG_PASSWORD_HASH = "wrong_hash";

    @Test
    public void testGetUsername() {
        Account account = new Account(new Username(TEST_USERNAME), new Password(TEST_PASSWORD_HASH));
        assertEquals(new Username(TEST_USERNAME), account.getUsername());
    }

    @Test
    public void testGetPasswordHash() {
        Account account = new Account(new Username(TEST_USERNAME), new Password(TEST_PASSWORD_HASH));
        assertEquals(TEST_PASSWORD_HASH, account.getPasswordHash().getHashedPassword());
    }
}
