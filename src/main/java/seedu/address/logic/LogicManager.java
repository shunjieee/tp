package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.account.exception.AccountException;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.commands.LogoutCommand;
import seedu.address.logic.commands.RegisterCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AccountManagerParser;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private Model model;
    private Storage storage;
    private final AddressBookParser addressBookParser;

    private AccountManagerParser accountManagerParser = new AccountManagerParser();

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public CommandResult execute(String commandText) throws AccountException, CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        CommandResult commandResult;

        Command command = (Command) AccountManagerParser.parseCommand(commandText).get(0);
        boolean isUserLogin = (boolean) AccountManagerParser.parseCommand(commandText).get(1);

        if (command instanceof LoginCommand && isUserLogin) {
            throw new CommandException("You are already logged in.");
        }

        if (command instanceof LogoutCommand && !isUserLogin) {
            throw new CommandException("You are not logged in.");
        }

        if (command instanceof LogoutCommand && isUserLogin) {
            AccountManager accountManager = accountManagerParser.getAccountManager();
            LogoutCommand logoutrCommand = (LogoutCommand) command;
            logoutrCommand.setAccountManager(accountManager);
            commandResult = command.execute(model);
            return commandResult;
        }

        if (command instanceof RegisterCommand && isUserLogin) {
            AccountManager accountManager = accountManagerParser.getAccountManager();
            RegisterCommand registerCommand = (RegisterCommand) command;
            registerCommand.setAccountManager(accountManager);
            commandResult = command.execute(model);
            return commandResult;
        }

        if (isUserLogin) {
            if (command instanceof LogoutCommand) {
                AccountManager accountManager = accountManagerParser.getAccountManager();
                LogoutCommand logoutCommand = (LogoutCommand) command;
                logoutCommand.setAccountManager(accountManager);
                commandResult = command.execute(model);
            }
            command = addressBookParser.parseCommand(commandText);
            commandResult = command.execute(model);

            try {
                storage.saveAddressBook(model.getAddressBook());
                storage.saveUserPrefs(model.getUserPrefs());
            } catch (AccessDeniedException e) {
                throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
            } catch (IOException ioe) {
                throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
            }

        } else {
            AccountManager accountManager = accountManagerParser.getAccountManager();
            if (command instanceof LoginCommand) {
                LoginCommand loginCommand = (LoginCommand) command;
                loginCommand.setAccountManager(accountManager);
            } else if (command instanceof RegisterCommand) {
                RegisterCommand registerCommand = (RegisterCommand) command;
                registerCommand.setAccountManager(accountManager);
            }
            commandResult = command.execute(model);
        }

        return commandResult;
    }

    @Override
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public void linkAccountManagerToParser(AccountManager accountManager) {
        accountManagerParser.setAccountManager(accountManager);
    }
}
