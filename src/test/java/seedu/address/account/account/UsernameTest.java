package seedu.address.account.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UsernameTest {
    private static final String TEST_USERNAME = "test";

    @Test
    public void testGetUsername() {
        Username username = new Username(TEST_USERNAME);
        assertEquals(TEST_USERNAME, username.getUsername());
    }
}
