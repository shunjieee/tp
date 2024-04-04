package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label saveLocationStatus;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        String filePath = Paths.get(".").resolve(saveLocation).toString();
        if (!filePath.equals("./data/addressbook.json")) {
            String[] parts = filePath.split("/");
            String filename = parts[parts.length - 1];
            String username = filename.replace("AddressBook.json", "");
            String statusText = "User " + username + " has logged in";
            saveLocationStatus.setText(statusText);
        } else {
            String statusText = "No user currently logged in";
            saveLocationStatus.setText(statusText);
        }
    }
}
