package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;


/**
 * Parses input arguments and creates a new DeleteTagCommand object
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTagCommand
     * and returns a DeleteTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
        }

        try {
            return new DeleteTagCommand(new Tag(trimmedArgs));
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }
}
