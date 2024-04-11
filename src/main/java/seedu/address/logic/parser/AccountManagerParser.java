package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.account.exception.AccountException;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.AccountManager;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.commands.LogoutCommand;
import seedu.address.logic.commands.RegisterCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AccountManagerParser {
    private static AccountManager accountManager;
    private static boolean isUserLogin = false;

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public static Command parseCommand(String userInput) throws AccountException, ParseException {
        isUserLogin = accountManager.getLoginStatus();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches() && !isUserLogin) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {
        case LoginCommand.COMMAND_WORD:
            LoginCommand loginCommand = new LoginCommandParser().parse(arguments);
            loginCommand.setAccountManager(accountManager);
            return loginCommand;

        case RegisterCommand.COMMAND_WORD:
            RegisterCommand registerCommand = new RegisterCommandParser().parse(arguments);
            registerCommand.setAccountManager(accountManager);
            return registerCommand;

        case HelpCommand.COMMAND_WORD:
            Command helpCommand = new HelpCommand();
            return helpCommand;

        case ExitCommand.COMMAND_WORD:
            Command exitCommand = new ExitCommand();
            return exitCommand;

        case LogoutCommand.COMMAND_WORD:
            LogoutCommand logoutCommand = new LogoutCommand();
            logoutCommand.setAccountManager(accountManager);
            return logoutCommand;

        default:
            logger.finer("User hasn't logged in, cannot parse and execute: " + userInput);
            if (isUserLogin) {
                return null;
            } else {
                throw new AccountException("Please login first.");
            }
        }
    }

    /**
     * Sets the account manager for the parser.
     *
     * @param accountManager the account manager to be set
     */
    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Gets the account manager for the parser.
     *
     * @return the account manager
     */
    public AccountManager getAccountManager() {
        return accountManager;
    }
}

