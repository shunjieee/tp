package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.AccountManager;
import seedu.address.model.Model;

/**
 * Represents a Logout command with hidden internal logic and the ability to be executed.
 */
public class LogoutCommand extends Command {

    /**
     * The keyword that identifies this command.
     */
    public static final String COMMAND_WORD = "logout";

    /**
     * The message shown to the user after successful logout.
     */
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "You have logged out.";

    /**
     * The account manager that handles the logout process.
     */
    private AccountManager accountManager;

    /**
     * Sets the account manager for this command.
     *
     * @param accountManager The account manager to be set.
     */
    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Executes the logout command.
     *
     * @param model The model to be used in command execution.
     * @return A CommandResult representing the result of the logout operation.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(accountManager);

        accountManager.logout();

        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false, false, true);
    }
}
