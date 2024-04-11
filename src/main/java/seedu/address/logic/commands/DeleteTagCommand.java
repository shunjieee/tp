package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_TAG_NOT_IN_TAG_LIST;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Add a valid tag to the tag list.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "tag-";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Remove a tag from the tag list.\n"
            + "Format: tag- TAG\n"
            + "Example: tag- Finance\n"
            + "NOTE: Tags are case-sensitive.";

    public static final String MESSAGE_SUCCESS = "Tag removed: %1$s";

    public static final String MESSAGE_PERSON_CONTAIN_TAG = "contains %1$s.\n%1$s NOT deleted!";

    private final Tag toDelete;


    /**
     * Creates an AddTagCommand to add the specified {@code Tag}
     */
    public DeleteTagCommand(Tag tag) {
        requireNonNull(tag);
        toDelete = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(toDelete)) {
            throw new CommandException(String.format(MESSAGE_TAG_NOT_IN_TAG_LIST, toDelete));
        }

        List<Person> personsWithToDeleteTag = new ArrayList<>();
        for (Person person : model.getFilteredPersonList()) {
            if (person.getTags().contains(toDelete)) {
                personsWithToDeleteTag.add(person);
            }
        }

        if (!personsWithToDeleteTag.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Person p : personsWithToDeleteTag) {
                System.out.println(p.getName());
                sb.append(p.getName() + ", ");
            }
            sb.append(String.format(MESSAGE_PERSON_CONTAIN_TAG, toDelete));
            throw new CommandException(sb.toString());
        }

        model.deleteTag(toDelete);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

}
