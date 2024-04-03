package seedu.address.account.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AccountTest {
    private static final String TEST_USERNAME = "test";
    private static final String TEST_PASSWORD_HASH = "5f4dcc3b5aa765d61d8327deb882cf99";
    private static final String WRONG_PASSWORD_HASH = "wrong_hash";

    @Test
    public void testGetUsername() {
        Account account = new Account(new Username(TEST_USERNAME), TEST_PASSWORD_HASH);
        assertEquals(new Username(TEST_USERNAME), account.getUsername());
    }

    @Test
    public void testGetPasswordHash() {
        Account account = new Account(new Username(TEST_USERNAME), TEST_PASSWORD_HASH);
        assertEquals(TEST_PASSWORD_HASH, account.getPasswordHash());
    }

    @Test
    public void testSetPasswordHash() {
        Account account = new Account(new Username(TEST_USERNAME), TEST_PASSWORD_HASH);
        account.setPasswordHash(WRONG_PASSWORD_HASH);
        assertEquals(WRONG_PASSWORD_HASH, account.getPasswordHash());
    }

    @Test
    public void testCheckPassword() {
        Account account = new Account(new Username(TEST_USERNAME), TEST_PASSWORD_HASH);
        assertEquals(true, account.checkPassword(TEST_PASSWORD_HASH));
        assertEquals(false, account.checkPassword(WRONG_PASSWORD_HASH));
    }

    @Test
    public void testCheckUsername() {
        Account account = new Account(new Username(TEST_USERNAME), TEST_PASSWORD_HASH);
        assertEquals(true, account.checkUsername(new Username(TEST_USERNAME)));
    }
}
