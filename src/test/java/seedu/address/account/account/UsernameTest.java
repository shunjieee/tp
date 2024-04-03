package seedu.address.account.account;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.address.account.account.Username;

public class UsernameTest {
    private static final String TEST_USERNAME = "test";

    @Test
    public void testGetUsername() {
        Username username = new Username(TEST_USERNAME);
        assertEquals(TEST_USERNAME, username.getUsername());
    }
}
