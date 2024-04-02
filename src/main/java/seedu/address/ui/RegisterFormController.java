package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import seedu.address.account.account.Account;
import seedu.address.account.account.Username;
import seedu.address.logic.AccountManager;

/**
 * Represents a controller for the registration form.
 * Each RegisterFormController is associated with a username field, a password field, and an account list.
 * The RegisterFormController handles the registration process, including input validation and user creation.
 */
public class RegisterFormController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private AccountManager accountManager;

    public RegisterFormController(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate the input fields here
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username and password cannot be empty");
            return;
        }

        if (!isValidUsername(username)) {
            showAlert("Error", "Invalid username. Username should be 4-10 alphanumeric characters.");
            return;
        }

        // If the input is valid, create a new Account and add it to the AccountList
        Username usernameObj = new Username(username);
        String passwordHash = accountManager.getAccountList().hashPassword(password);
        Account account = new Account(usernameObj, passwordHash, null);

        boolean success = accountManager.getAccountList().addAccount(account);
        if (success) {
            // Registration successful
            showAlert("Success", "Registration successful");
        } else {
            // Registration failed
            showAlert("Error", "Registration failed. Username already exists.");
        }
    }

    private boolean isValidUsername(String username) {
        return username != null && username.matches("[a-zA-Z0-9]{4,10}");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
