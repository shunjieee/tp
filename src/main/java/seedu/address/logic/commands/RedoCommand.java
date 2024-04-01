package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with the ability to redo the most recent redone command.
 * When executed, the command will attempt to restore the address book to its state
 * after the original action was performed.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redo the most recent redone command. ";
    public static final String MESSAGE_REDO_SUCCESS = "The command has been redone.";
    public static final String MESSAGE_REDO_FAILURE = "There is no more command to redo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoCommand()) {
            throw new CommandException(MESSAGE_REDO_FAILURE);
        }

        model.redoCommand();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_REDO_SUCCESS);
    }

}
