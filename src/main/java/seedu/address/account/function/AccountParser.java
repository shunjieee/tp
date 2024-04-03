package seedu.address.account.function;

import java.util.ArrayList;
import java.util.List;

import seedu.address.account.account.Account;
import seedu.address.account.account.Username;

/**
 * Represents a parser for Account data.
 * Each AccountParser is able to parse a list of account data strings into Account objects, and vice versa.
 */
public class AccountParser {
    /**
     * Parses a list of account data strings into a list of Account objects.
     *
     * @param accountData The list of account data strings to be parsed.
     * @return A list of Account objects parsed from the account data strings.
     */
    public List<Account> parseToAccount(List<String> accountData) {
        List<Account> accounts = new ArrayList<>();

        for (String data : accountData) {
            String[] parts = data.split("\\|");
            String username = parts[0].split(":")[1].trim();
            String passwordHash = parts[1].split(":")[1].trim();

            Username usernameObj = new Username(username);
            Account account = new Account(usernameObj, passwordHash);
            accounts.add(account);
        }

        return accounts;
    }

    /**
     * Parses a list of Account objects into a list of account data strings.
     *
     * @param accounts The list of Account objects to be unparsed.
     * @return A list of account data strings unparsed from the Account objects.
     */
    public List<String> parseToString(List<Account> accounts) {
        List<String> accountData = new ArrayList<>();

        for (Account account : accounts) {
            String data = account.toString();
            accountData.add(data);
        }

        return accountData;
    }
}
