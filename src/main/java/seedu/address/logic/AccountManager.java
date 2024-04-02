package seedu.address.logic;

import java.io.IOException;
import java.util.List;

import seedu.address.account.account.Account;
import seedu.address.account.account.AccountList;
import seedu.address.account.function.AccountParser;
import seedu.address.account.function.AccountStorage;
import seedu.address.model.AddressBook;

/**
 * Manages the current session, including the logged-in account and the associated logic manager.
 */
public class AccountManager {
    private AccountList accountList;
    private AccountParser accountParser = new AccountParser();
    private Account currentAccount;
    private Logic logic;

    /**
     * Constructs an AccountManager.
     * It loads the accounts from a file and stores them in an AccountList.
     * If the file cannot be read, an empty AccountList is created.
     */
    public AccountManager() {
        AccountStorage accountStorage = new AccountStorage("accounts.txt"); // replace with your actual file path
        try {
            List<String> accountListInString = accountStorage.load();
            List<Account> accountListInAccount = accountParser.parseToAccount(accountListInString);
            this.accountList = new AccountList();
            for (Account account : accountListInAccount) {
                this.accountList.addAccount(account);
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.accountList = new AccountList();
        }
        currentAccount = null;
        logic = null;
    }

    /**
     * Logs in the specified account and loads the associated address book.
     *
     * @param account The account to log in.
     */
    public void login(Account account) {
        this.currentAccount = account;

    }

    /**
     * Logs out the current account and clears the address book.
     */
    public void logout() {
        this.currentAccount = null;
        //this.addressBook = null;
    }

    /**
     * Loads the address book associated with the specified account.
     *
     * @param account The account whose address book is to be loaded.
     * @return The loaded address book.
     */
    private AddressBook loadAddressBook(Account account) {
        // Implementation omitted for brevity
        return null;
    }

    /**
     * Returns the current account.
     *
     * @return The current account.
     */
    public Account getCurrentAccount() {
        return currentAccount;
    }

    public AccountList getAccountList() {
        return accountList;
    }

}
