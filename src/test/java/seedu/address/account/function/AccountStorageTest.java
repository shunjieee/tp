package seedu.address.account.function;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AccountStorageTest {
    private static final String TEST_FILE_PATH = "testFilePath.txt";

    @Test
    public void testLoad() throws IOException {
        // Prepare test data
        String testData = "username: test | passwordHash: 5f4dcc3b5aa765d61d8327deb882cf99";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE_PATH))) {
            writer.write(testData);
        }

        // Test AccountStorage.load()
        AccountStorage accountStorage = new AccountStorage(TEST_FILE_PATH);
        List<String> accounts = accountStorage.load();

        // Verify results
        assertEquals(1, accounts.size());
        assertEquals(testData, accounts.get(0));

        // Clean up test file
        Files.delete(Path.of(TEST_FILE_PATH));
    }

    @Test
    public void testSave() throws IOException {
        // Prepare test data
        List<String> accounts = new ArrayList<>();
        accounts.add("username: test | passwordHash: 5f4dcc3b5aa765d61d8327deb882cf99");

        // Test AccountStorage.save()
        AccountStorage accountStorage = new AccountStorage(TEST_FILE_PATH);
        accountStorage.save(accounts);

        // Verify that the data was written to the file correctly
        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_FILE_PATH))) {
            String line = reader.readLine();
            assertEquals("username: test | passwordHash: 5f4dcc3b5aa765d61d8327deb882cf99", line);
        }

        // Clean up test file
        Files.delete(Path.of(TEST_FILE_PATH));
    }
}
