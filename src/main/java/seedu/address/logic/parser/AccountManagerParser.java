package seedu.address.logic.parser;

import seedu.address.account.exception.AccountException;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.AccountManager;
import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

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
    public static ArrayList<Object> parseCommand(String userInput) throws AccountException, ParseException {
        ArrayList<Object> result = new ArrayList<>();
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
                Object loginCommand = new LoginCommandParser().parse(arguments);
                result.add(loginCommand);
                result.add(isUserLogin);
                return result;

            case RegisterCommand.COMMAND_WORD:
                Object registerCommand = new RegisterCommandParser().parse(arguments);
                result.add(registerCommand);
                result.add(isUserLogin);
                return result;

            case HelpCommand.COMMAND_WORD:
                Object helpCommand = new HelpCommand();
                result.add(helpCommand);
                result.add(isUserLogin);
                return result;

            case ExitCommand.COMMAND_WORD:
                Object exitCommand = new ExitCommand();
                result.add(exitCommand);
                result.add(isUserLogin);
                return result;

            default:
                logger.finer("User hasn't logged in, cannot parse and execute: " + userInput);
                throw new AccountException("Please login first.");
        }
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }
}

