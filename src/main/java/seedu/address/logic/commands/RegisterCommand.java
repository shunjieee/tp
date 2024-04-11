package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import seedu.address.account.account.Account;
import seedu.address.logic.AccountManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a Register command with hidden internal logic and the ability to be executed.
 */
public class RegisterCommand extends Command {

    /**
     * The keyword that identifies this command.
     */
    public static final String COMMAND_WORD = "register";

    /**
     * The usage message shown to the user detailing how this command should be invoked.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Registers a new user. "
            + "Parameters: "
            + PREFIX_USERNAME + " USERNAME "
            + PREFIX_PASSWORD + " PASSWORD \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_USERNAME + " john1234 "
            + PREFIX_PASSWORD + " qweasd123 ";

    public static final String MESSAGE_REGISTER_SUCCESS = "Account registered successfully.";
    public static final String MESSAGE_REGISTER_FAILURE = "Account registration failed. Username already exists.";

    /**
     * The account to be registered.
     */
    private final Account account;

    /**
     * The account manager that handles the registration process.
     */
    private AccountManager accountManager;

    /**
     * Creates a RegisterCommand to register the specified {@code Account}.
     *
     * @param account The account to be registered.
     */
    public RegisterCommand(Account account) {
        this.account = account;
    }

    /**
     * Sets the account manager for this command.
     *
     * @param accountManager The account manager to be set.
     */
    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Executes the register command.
     *
     * @param model The model to be used in command execution.
     * @return A CommandResult representing the result of the registration operation.
     * @throws CommandException If the registration operation fails.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(accountManager);

        boolean isAddSuccessful = accountManager.getAccountList().addAccount(account);

        if (isAddSuccessful) {
            return new CommandResult(MESSAGE_REGISTER_SUCCESS);
        } else {
            throw new CommandException(MESSAGE_REGISTER_FAILURE);
        }
    }
}
