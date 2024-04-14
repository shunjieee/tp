package seedu.address.account.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PasswordTest {
    private static final String TEST_PASSWORD_HASH = "5f4dcc3b5aa765d61d8327deb882cf99"; // md5 hash for "password"

    @Test
    public void testGetHashedPassword() {
        Password password = new Password(TEST_PASSWORD_HASH);
        assertEquals(TEST_PASSWORD_HASH, password.getHashedPassword());
    }

    @Test
    void testValidPasswordWithoutSpaces() {
        assertTrue(Password.checkValidity("password123"), "Password should be valid.");
        assertTrue(Password.checkValidity("CorrectHorse"), "Password should be valid.");
        assertTrue(Password.checkValidity("p@ssw0rd!"), "Password with special characters should be valid.");
        assertTrue(Password.checkValidity("Valid#Pass123"), "Password with special characters should be valid.");
    }

    @Test
    void testInvalidPasswordDueToLength() {
        assertFalse(Password.checkValidity("pass"), "Password too short should be invalid.");
        assertFalse(Password.checkValidity("thispasswordiswaytoolongfortheconstraints"),
                "Password too long should be invalid.");
    }

    @Test
    void testInvalidPasswordDueToSpaces() {
        assertFalse(Password.checkValidity("password with spaces"), "Password with spaces should be invalid.");
        assertFalse(Password.checkValidity(" two spaces "),
                "Password with leading and trailing spaces should be invalid.");
    }

    @Test
    void testBoundaryCasesForLength() {
        assertTrue(Password.checkValidity("123456"), "Password exactly 6 characters long should be valid.");
        assertTrue(Password.checkValidity("12345678901234567890"),
                "Password exactly 20 characters long should be valid.");
        assertFalse(Password.checkValidity("12345"), "Password shorter than 6 characters should be invalid.");
        assertFalse(Password.checkValidity("123456789012345678901"),
                "Password longer than 20 characters should be invalid.");
    }

    @Test
    void testSpecialCases() {
        assertFalse(Password.checkValidity(""), "Empty password should be invalid.");
        assertFalse(Password.checkValidity(null), "Null password should be invalid.");
    }
}
