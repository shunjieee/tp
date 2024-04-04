package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import seedu.address.account.account.Account;
import seedu.address.logic.AccountManager;

/**
 * Represents a controller for the login form.
 * Each LoginFormController is associated with a username field, a password field, and an account list.
 * The LoginFormController handles the login process, including input validation and user authentication.
 */
public class LoginFormController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private AccountManager accountManager;
    private MainWindow mainWindow;

    /**
     * Constructs a LoginFormController with the specified AccountManager and MainWindow.
     *
     * @param accountManager The AccountManager to be used for user authentication.
     * @param mainWindow The MainWindow to be updated upon successful login.
     */
    public LoginFormController(AccountManager accountManager, MainWindow mainWindow) {
        this.accountManager = accountManager;
        this.mainWindow = mainWindow;
    }

    @FXML
    private void handleLogin() {
        if (accountManager.getLoginStatus()) {
            showAlert("Error", "You are already logged in. Please log out first.");
            return;
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate the input fields here
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username and password cannot be empty");
            return;
        }

        // If the input is valid, authenticate the user
        String passwordHash = accountManager.getAccountList().hashPassword(password);
        Account account = accountManager.getAccountList().authenticate(username, passwordHash);
        if (account != null) {
            // Authentication successful
            showAlert("Success", "Login successful");
            accountManager.login(account);
            mainWindow.fillInnerParts();
        } else {
            // Authentication failed
            showAlert("Error", "Login failed. Invalid username or password.");
        }
    }

    /**
     * Displays an alert with the specified title and message.
     *
     * @param title The title of the alert.
     * @param message The message to be displayed in the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
