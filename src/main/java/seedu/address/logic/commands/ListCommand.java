package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.function.Predicate;

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
    private final String keywords;

    /**
     * Creates a {@code ListCommand} to filter the addressbook based on tags, which
     * are searched via the keywords / user's input.
     *
     * @param predicate The predicate to check if the addressbook contains the correct tag(s).
     * @param keywords User's input
     */
    public ListCommand(Predicate<Person> predicate, String keywords) {
        this.predicate = predicate;
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        if (predicate.equals(PREDICATE_SHOW_ALL_PERSONS)) {
            String feedbackToUser = MESSAGE_SUCCESS + " in " + keywords + ".";
            String expected = "Listed all persons in the addressbook.";
            assert feedbackToUser != expected : "Logic / Message error";
        }
        return new CommandResult(MESSAGE_SUCCESS + " in " + keywords + ".");
    }
}
