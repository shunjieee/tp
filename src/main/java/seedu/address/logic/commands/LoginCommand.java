package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import seedu.address.account.account.Account;

public class LoginCommand {
    public static final String COMMAND_WORD = "login";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Logs in the user. "
            + "Parameters: "
            + PREFIX_USERNAME + "USERNAME "
            + PREFIX_PASSWORD + "PASSWORD \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_USERNAME + "john1234 "
            + PREFIX_PASSWORD + "qweasd123 ";

    private final Account account;

    public LoginCommand(Account account) {
        this.account = account;
    }
}
