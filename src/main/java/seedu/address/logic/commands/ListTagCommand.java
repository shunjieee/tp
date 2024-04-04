package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all tags in the tag list to the user.
 */
public class ListTagCommand extends ListCommand{
    
    public ListTagCommand() {
        super(null, null);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(model.listTags());
    }
}
