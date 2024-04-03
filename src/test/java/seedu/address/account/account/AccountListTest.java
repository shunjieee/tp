package seedu.address.account.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AccountListTest {
    private static final String TEST_USERNAME = "test";
    private static final String TEST_PASSWORD = "password";

    @Test
    public void testAddAccount() {
        AccountList accountList = new AccountList();
        Account account = new Account(new Username(TEST_USERNAME), accountList.hashPassword(TEST_PASSWORD));
        assertTrue(accountList.addAccount(account));
    }

    @Test
    public void testAuthenticate() {
        AccountList accountList = new AccountList();
        Account account = new Account(new Username(TEST_USERNAME), accountList.hashPassword(TEST_PASSWORD));
        accountList.addAccount(account);
        assertNotNull(accountList.authenticate(TEST_USERNAME, accountList.hashPassword(TEST_PASSWORD)));
    }

    @Test
    public void testHashPassword() {
        AccountList accountList = new AccountList();
        String hashedPassword = accountList.hashPassword(TEST_PASSWORD);
        assertEquals(64, hashedPassword.length());
    }
}
