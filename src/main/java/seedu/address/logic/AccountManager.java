package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.account.account.Account;
import seedu.address.account.account.AccountList;
import seedu.address.account.account.Username;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.TagList;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonTagListStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.MainWindow;

/**
 * Manages the current session, including the logged-in account and the associated logic manager.
 */
public class AccountManager {
    private AccountList accountList = new AccountList();
    private Account currentAccount;
    private Logic logic;

    private boolean isUserLogin = false;

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private MainWindow mainWindow;

    /**
     * Constructs an AccountManager.
     * It loads the accounts from a file and stores them in an AccountList.
     * If the file cannot be read, an empty AccountList is created.
     */
    public AccountManager(Logic logic) {
        try {
            accountList.loadFromFile();
        } catch (IOException e) {
            e.printStackTrace();
            this.accountList = new AccountList();
        }
        currentAccount = null;
        this.logic = logic;
    }

    /**
     * Logs in the specified account and loads the associated address book.
     *
     * @param account The account to log in.
     */
    public void login(Account account) {
        this.currentAccount = account;
        Username username = account.getUsername();
        updateModelManagerForUser(username.getUsername());
        isUserLogin = true;
    }

    /**
     * Logs out the current account and clears the address book.
     */
    public void logout() {
        this.currentAccount = null;
        this.isUserLogin = false;
        clearModelManagerAfterLogOut();
    }

    /**
     * Returns the current account.
     *
     * @return The current account.
     */

    public AccountList getAccountList() {
        return accountList;
    }

    private void updateModelManagerForUser(String username) {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(Paths.get("data", username + ".json"));
        UserPrefs userPrefs = loadUserPrefs(userPrefsStorage);
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(
                Paths.get("data", username + "addressbook.json"));
        JsonTagListStorage tagListStorage = new JsonTagListStorage(Paths.get("data", "taglist.json"));

        Storage storage = new StorageManager(addressBookStorage, userPrefsStorage, tagListStorage);
        logger.info("Using data file : " + storage.getAddressBookFilePath());

        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialData;

        Optional<TagList> tagListOptional;
        TagList initialTagList;

        try {
            addressBookOptional = storage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getAddressBookFilePath()
                        + " populated with a sample AddressBook.");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);

            tagListOptional = storage.readTagList();
            if (!tagListOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getTagListFilePath());
            }
            initialTagList = tagListOptional.orElse(new TagList());

        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getAddressBookFilePath()
                    + " and / or " + storage.getTagListFilePath()
                    + " could not be loaded."
                    + " Will be starting with an empty AddressBook and / or tag list.");
            initialData = new AddressBook();
            initialTagList = new TagList();
        }

        logic.setModel(new ModelManager(initialData, userPrefs, initialTagList));
        logic.setStorage(storage);
        System.out.println("ModelManager updated for user: " + userPrefs.getAddressBookFilePath());
    }

    private void clearModelManagerAfterLogOut() {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(Paths.get("data", "preferences.json"));
        UserPrefs userPrefs = clearUserPrefs(userPrefsStorage);
        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(userPrefs.getAddressBookFilePath());
        JsonTagListStorage tagListStorage = new JsonTagListStorage(Paths.get("data", "taglist.json"));
        Storage storage = new StorageManager(addressBookStorage, userPrefsStorage, tagListStorage);

        logger.info("Using data file : " + storage.getAddressBookFilePath());

        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialData;

        Optional<TagList> tagListOptional;
        TagList initialTagList;

        try {
            addressBookOptional = storage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getAddressBookFilePath()
                        + " populated with a sample AddressBook.");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);

            tagListOptional = storage.readTagList();
            if (!tagListOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getTagListFilePath());
            }
            initialTagList = tagListOptional.orElse(new TagList());

        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getAddressBookFilePath()
                    + " and / or " + storage.getTagListFilePath()
                    + " could not be loaded."
                    + " Will be starting with an empty AddressBook and / or tag list.");
            initialData = new AddressBook();
            initialTagList = new TagList();
        }

        logic.setModel(new ModelManager(initialData, userPrefs, initialTagList));
    }

    protected UserPrefs loadUserPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using preference file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            if (!prefsOptional.isPresent()) {
                logger.info("Creating new preference file " + prefsFilePath);
            }
            initializedPrefs = prefsOptional.orElse(new UserPrefs());

        } catch (DataLoadingException e) {
            logger.warning("Preference file at " + prefsFilePath + " could not be loaded."
                    + " Using default preferences.");
            initializedPrefs = new UserPrefs();
        }
        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            initializedPrefs.setAddressBookFilePath(
                    Paths.get("data", currentAccount.getUsername().getUsername() + "AddressBook.json"));
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    protected UserPrefs clearUserPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = Paths.get("preferences.json");
        logger.info("Using preference file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            if (!prefsOptional.isPresent()) {
                logger.info("Creating new preference file " + prefsFilePath);
            }
            initializedPrefs = prefsOptional.orElse(new UserPrefs());

        } catch (DataLoadingException e) {
            logger.warning("Preference file at " + prefsFilePath + " could not be loaded."
                    + " Using default preferences.");
            initializedPrefs = new UserPrefs();
        }

        return initializedPrefs;
    }

    public boolean getLoginStatus() {
        return isUserLogin;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }
}
