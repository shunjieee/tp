package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with the ability to undo the last executed command.
 * When executed, the command will attempt to revert the address book to the previous state.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo the last executed command. ";
    public static final String MESSAGE_UNDO_SUCCESS = "The command has been undone.";
    public static final String MESSAGE_UNDO_FAILURE = "There is no more command to undo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoCommand()) {
            throw new CommandException(MESSAGE_UNDO_FAILURE);
        }

        model.undoCommand();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_UNDO_SUCCESS);
    }

}
