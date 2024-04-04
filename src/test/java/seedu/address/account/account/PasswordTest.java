package seedu.address.account.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PasswordTest {
    private static final String TEST_PASSWORD_HASH = "5f4dcc3b5aa765d61d8327deb882cf99"; // md5 hash for "password"

    @Test
    public void testGetHashedPassword() {
        Password password = new Password(TEST_PASSWORD_HASH);
        assertEquals(TEST_PASSWORD_HASH, password.getHashedPassword());
    }
}
