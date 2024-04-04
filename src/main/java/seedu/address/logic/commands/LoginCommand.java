package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import seedu.address.account.account.Account;
import seedu.address.account.account.Username;
import seedu.address.logic.AccountManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class LoginCommand extends Command {
    public static final String COMMAND_WORD = "login";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Logs in the user. "
            + "Parameters: "
            + PREFIX_USERNAME + "USERNAME "
            + PREFIX_PASSWORD + "PASSWORD \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_USERNAME + " john1234 "
            + PREFIX_PASSWORD + " qweasd123 ";

    private final Account account;
    private AccountManager accountManager;

    public LoginCommand(Account account) {
        this.account = account;
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(accountManager);

        String passwordHashed = accountManager.getAccountList().hashPassword(account.getPasswordHash());
        Username username = account.getUsername();
        Account accountToLogin = accountManager.getAccountList().authenticate(username.getUsername(), passwordHashed);

        if (accountToLogin != null) {
            accountManager.login(accountToLogin);
            return new CommandResult("Login successfully.");
        } else {
            throw new CommandException("Login failed. Invalid username or password.");
        }
    }
}
