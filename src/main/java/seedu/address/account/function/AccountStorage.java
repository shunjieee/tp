package seedu.address.account.function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a storage for Account data.
 * Each AccountStorage is associated with a file path where the account data is stored.
 */
public class AccountStorage {
    private final String filePath;

    public AccountStorage(String filePath) {

        this.filePath = filePath;
        createParentDirectoryIfNeeded(filePath);
    }

    /**
     * Loads the account data from the file.
     *
     * @return A list of account data strings loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public List<String> load() throws IOException {
        List<String> accounts = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                accounts.add(line);
            }
        }

        return accounts;
    }

    /**
     * Saves the account data to the file.
     *
     * @param accounts The list of account data strings to be saved to the file.
     * @throws IOException If an I/O error occurs.
     */
    public void save(List<String> accounts) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String account : accounts) {
                writer.write(account);
                writer.newLine();
            }
        }
    }

    private void createParentDirectoryIfNeeded(String filePath) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }
}
