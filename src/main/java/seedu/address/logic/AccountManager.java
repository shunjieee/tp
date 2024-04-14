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

/**
 * Manages user accounts, session states (such as login status),
 * and interactions between the user data and the application logic.
 * This class is responsible for handling the login and logout processes,
 * loading and saving user preferences, and ensuring that
 * the user's data (address book, preferences, and tag lists) is correctly loaded and saved to the filesystem.
 * It works closely with the application's logic layer to update the application state based on the current user.
 */
public class AccountManager {
    private AccountList accountList = new AccountList();
    private Account currentAccount;
    private Logic logic;

    private boolean isUserLogin = false;

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);


    /**
     * Constructs an AccountManager instance, initializing the account list by loading accounts from a file.
     * If the accounts file cannot be read, an empty AccountList is instantiated. It also links this account manager
     * to the application's logic processing unit.
     *
     * @param logic The Logic object that handles the application's logic and data manipulation.
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
        this.logic.linkAccountManagerToParser(this);
    }

    /**
     * Logs in the specified account by setting it as the current account and loading its associated data.
     * It triggers the update of the ModelManager for the user to
     * ensure the application's state reflects the user's data.
     *
     * @param account The account object representing the user that is logging in.
     */
    public void login(Account account) {
        this.currentAccount = account;
        Username username = account.getUsername();
        updateModelManagerForUser(username.getUsername());
        isUserLogin = true;
    }

    /**
     * Logs out the current user, clears the current account information,
     * and resets the application's data to a default state.
     * This method ensures that no user data is accidentally retained in the application state after logout.
     */
    public void logout() {
        this.currentAccount = null;
        clearModelManagerAfterLogOut();
        this.isUserLogin = false;
    }

    /**
     * Retrieves the current account list, which includes all user accounts managed by this application.
     *
     * @return The AccountList containing all user accounts.
     */

    public AccountList getAccountList() {
        return accountList;
    }

    /**
     * Updates the ModelManager with the preferences and data of the specified user.
     * This method ensures that the application's
     * logic and data layers are synchronized with the user's saved data,
     * including the address book, user preferences, and tags.
     *
     * @param username The username of the account whose data should be loaded into the ModelManager.
     */
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
            initialTagList = tagListOptional.orElse(TagList.getSampleTagList());

        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getAddressBookFilePath()
                    + " and / or " + storage.getTagListFilePath()
                    + " could not be loaded."
                    + " Will be starting with an empty AddressBook and / or tag list.");
            initialData = new AddressBook();
            initialTagList = TagList.getSampleTagList();
        }

        logic.setModel(new ModelManager(initialData, userPrefs, initialTagList));
        logic.setStorage(storage);
        System.out.println("ModelManager updated for user: " + userPrefs.getAddressBookFilePath());
    }

    /**
     * Clears the ModelManager data after a user logs out.
     * This method resets the application's state to a default configuration,
     * ensuring that no user data is retained after logout.
     */
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

    /**
     * Loads the user preferences from the filesystem based on the current account's settings.
     * If the preferences file does not exist, it creates a new one with default settings.
     *
     * @param storage The storage handler responsible for reading and saving user preferences.
     * @return The UserPrefs object containing the loaded or default user preferences.
     */
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

    /**
     * Clears user preferences to their default values. This method is typically called during logout
     * to ensure that any user-specific settings are not retained in the application.
     *
     * @param storage The storage handler responsible for user preferences.
     * @return The UserPrefs object containing the reset preferences.
     */
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

    /**
     * Returns the login status of the current session.
     *
     * @return true if a user is currently logged in, false otherwise.
     */
    public boolean getLoginStatus() {
        return isUserLogin;
    }

}
