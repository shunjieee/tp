package seedu.address.account.exception;

/**
 * Represents an exception related to account operations.
 * This exception is thrown when an account operation fails due to reasons such as invalid input or data inconsistency.
 */
public class AccountException extends Exception {
    /**
     * Constructs an AccountException instance with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public AccountException(String message) {
        super(message);
    }
}
