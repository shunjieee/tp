package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Add a valid tag to the tag list.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "tag+";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a tag to the tag list.\n"
            + "Format: tag+ TAG\n"
            + "Example: tag+ CEO\n"
            + "NOTE: Tags are case-sensitive.";

    public static final String MESSAGE_SUCCESS = "New tag added: %1$s";

    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists.";

    private final Tag toAdd;


    /**
     * Creates an AddTagCommand to add the specified {@code Tag}
     */
    public AddTagCommand(Tag tag) {
        requireNonNull(tag);
        toAdd = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTag(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }
        model.addTag(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

}
