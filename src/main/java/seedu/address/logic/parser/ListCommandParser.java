package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Arrays;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * ListCommand
     * and returns a ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        if (args.equals(" -a")) {
            return new ListCommand(PREDICATE_SHOW_ALL_PERSONS, "the addressbook");

        } else if (args.equals(" -t")) {
            return new ListTagCommand();

        } else {
            String trimmedArgs = args.trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
            }

            String[] tagKeywords = trimmedArgs.split("\\s+");

            return new ListCommand(new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)), args);
        }
    }

}
