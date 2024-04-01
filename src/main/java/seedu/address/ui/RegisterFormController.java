package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import seedu.address.account.account.Account;
import seedu.address.account.account.AccountList;
import seedu.address.account.account.Username;

public class RegisterFormController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private AccountList accountList;

    public RegisterFormController(AccountList accountList) {
        this.accountList = accountList;
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
        String passwordHash = accountList.hashPassword(password);
        Account account = new Account(usernameObj, passwordHash, null);

        boolean success = accountList.addAccount(account);
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
