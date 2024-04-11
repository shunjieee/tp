package seedu.address.account.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UsernameTest {
    private static final String TEST_USERNAME = "test";

    @Test
    public void testGetUsername() {
        Username username = new Username(TEST_USERNAME);
        assertEquals(TEST_USERNAME, username.getUsername());
    }

    @Test
    void testValidUsernames() {
        assertTrue(Username.checkValidity("user1"), "Alphanumeric 5 characters should be valid.");
        assertTrue(Username.checkValidity("user1234"), "Alphanumeric 8 characters should be valid.");
    }

    @Test
    void testInvalidUsernamesDueToLength() {
        assertFalse(Username.checkValidity("usr"), "Username too short should be invalid.");
        assertFalse(Username.checkValidity("user1234567"), "Username too long should be invalid.");
    }

    @Test
    void testInvalidUsernamesDueToNonAlphanumericCharacters() {
        assertFalse(Username.checkValidity("user!"), "Username with special character should be invalid.");
        assertFalse(Username.checkValidity("user name"), "Username with space should be invalid.");
    }

    @Test
    void testBoundaryCasesForLength() {
        assertTrue(Username.checkValidity("user"), "Username exactly 4 characters long should be valid.");
        assertTrue(Username.checkValidity("user123"), "Username exactly 7 characters long should be valid.");
        assertTrue(Username.checkValidity("user1234"), "Username exactly 8 characters long should be valid.");
        assertTrue(Username.checkValidity("user123457"), "Username exactly 10 characters long should be valid.");
        assertFalse(Username.checkValidity("123"), "Username shorter than 4 characters should be invalid.");
        assertFalse(Username.checkValidity("user1234567"), "Username longer than 10 characters should be invalid.");
    }

    @Test
    void testSpecialCases() {
        assertFalse(Username.checkValidity(""), "Empty username should be invalid.");
        assertFalse(Username.checkValidity(null), "Null username should be invalid.");
    }
}
