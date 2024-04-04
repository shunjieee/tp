package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import seedu.address.account.account.Account;
import seedu.address.logic.AccountManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class RegisterCommand extends Command {
    public static final String COMMAND_WORD = "register";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Registers a new user. "
            + "Parameters: "
            + PREFIX_USERNAME + "USERNAME "
            + PREFIX_PASSWORD + "PASSWORD \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_USERNAME + " john1234 "
            + PREFIX_PASSWORD + " qweasd123 ";

    public static final String MESSAGE_SUCCESS = "New user registered: %1$s";
    private final Account account;
    private AccountManager accountManager;

    public RegisterCommand(Account account) {
        this.account = account;
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(accountManager);

        String passwordHashed = accountManager.getAccountList().hashPassword(account.getPasswordHash());
        Account accountToRegister = new Account(account.getUsername(), passwordHashed);

        boolean success = accountManager.getAccountList().addAccount(accountToRegister);

        if (success) {
            return new CommandResult("Account registered successfully.");
        } else {
            throw new CommandException("Account registration failed. Username already exists.");
        }
    }
}
