package seedu.address.logic.commands;

import seedu.address.logic.AccountManager;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class LogoutCommand extends Command {
    public static final String COMMAND_WORD = "logout";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "You have logged out.";
    private AccountManager accountManager;

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(accountManager);

        accountManager.logout();

        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
    }


}
