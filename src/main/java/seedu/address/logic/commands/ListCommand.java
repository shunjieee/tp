package seedu.address.logic.commands;

import java.util.function.Predicate;
import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "ls";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all persons whose tags contain any of "
            + "the specified keywords (case-insensitive).\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie\n"
            + "`ls -a` list all persons in the contacts.";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    private final Predicate<Person> predicate;

    public ListCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
