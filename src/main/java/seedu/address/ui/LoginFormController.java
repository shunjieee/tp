package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import seedu.address.account.account.Account;
import seedu.address.account.account.AccountList;

public class LoginFormController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private AccountList accountList;

    public LoginFormController(AccountList accountList) {
        this.accountList = accountList;
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate the input fields here
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username and password cannot be empty");
            return;
        }

        // If the input is valid, authenticate the user
        String passwordHash = accountList.hashPassword(password);
        Account account = accountList.authenticate(username, passwordHash);
        if (account != null) {
            // Authentication successful
            showAlert("Success", "Login successful");
        } else {
            // Authentication failed
            showAlert("Error", "Login failed. Invalid username or password.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
