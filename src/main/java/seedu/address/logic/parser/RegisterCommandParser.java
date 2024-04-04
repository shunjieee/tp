package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;

import seedu.address.account.account.Account;
import seedu.address.account.account.Username;
import seedu.address.logic.commands.RegisterCommand;

/**
 * Parses input arguments and creates a new RegisterCommand object
 */
public class RegisterCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the RegisterCommand
     * and returns a RegisterCommand object for execution.
     */
    public RegisterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_USERNAME, PREFIX_PASSWORD);

        if (!arePrefixesPresent(argMultimap, PREFIX_USERNAME, PREFIX_PASSWORD)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RegisterCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_USERNAME, PREFIX_PASSWORD);
        Username username = ParserUtil.parseUsername(argMultimap.getValue(PREFIX_USERNAME).get());
        String password = ParserUtil.parsePassword(argMultimap.getValue(PREFIX_PASSWORD).get());

        Account account = new Account(username, password);

        return new RegisterCommand(account);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
