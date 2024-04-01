package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.person.Person;

/**
 * Maintains a list of all executed commands.
 * This class provides methods to log commands after execution,
 * and can be utilized to support undo/redo functionality by
 * keeping track of the command history.
 */
public class CommandList {
    private final List<Command> commandHistory;

    /**
     * The current index in the {@code commandHistory} list. This index tracks the position
     * up to which commands have been executed or undone, allowing for correct redo and undo operations.
     */
    private int currentCommandIndex;
    private Model model;

    /**
     * Constructs a new {@code CommandList} with an empty command history. This setup indicates
     * that no commands have been executed yet, initializing the current command index to -1
     * to reflect this state. The command history is initialized as an empty list, ready to
     * record executed commands for future undo and redo operations.
     */
    public CommandList() {
        commandHistory = new ArrayList<>();
        currentCommandIndex = -1;
    }

    public void linkToModel(Model model) {
        this.model = model;
    }

    /**
     * Adds the executed command to the command history.
     * This command can later be undone or redone.
     *
     * @param command The command that was executed and is to be added to the history.
     */
    public void addCommand(Command command) {
        // Before adding a new command, clear any commands that were executed
        // after the current command's position in the history.
        // This is necessary because executing a new command after an undo operation
        // invalidates the subsequent redo history.
        while (commandHistory.size() > currentCommandIndex + 1) {
            commandHistory.remove(commandHistory.size() - 1);
        }
        commandHistory.add(command);
        currentCommandIndex++;
    }

    /**
     * Reverts the most recent command, if possible.
     * This method identifies the type of the last executed command
     * and calls the corresponding undo method to reverse its effects.
     */
    public void undo() {
        Command lastCommand = commandHistory.get(currentCommandIndex);
        if (lastCommand instanceof AddCommand) {
            undoAdd((AddCommand) lastCommand);
        } else if (lastCommand instanceof DeleteCommand) {
            undoDelete((DeleteCommand) lastCommand);
        } else if (lastCommand instanceof EditCommand) {
            undoEdit((EditCommand) lastCommand);
        } else if (lastCommand instanceof ClearCommand) {
            undoClear((ClearCommand) lastCommand);
        }

        currentCommandIndex--;
    }

    /**
     * Reapplies the most recent undone command, if possible.
     * This method identifies the type of the last executed command
     * and calls the corresponding undo method to reverse its effects.
     */
    public void redo() {
        Command commandToRedo = commandHistory.get(currentCommandIndex + 1);
        if (commandToRedo instanceof AddCommand) {
            redoAdd((AddCommand) commandToRedo);
        } else if (commandToRedo instanceof DeleteCommand) {
            redoDelete((DeleteCommand) commandToRedo);
        } else if (commandToRedo instanceof EditCommand) {
            redoEdit((EditCommand) commandToRedo);
        } else if (commandToRedo instanceof ClearCommand) {
            redoClear((ClearCommand) commandToRedo);
        }

        currentCommandIndex++;
    }

    /**
     * Checks if there are commands available to undo.
     * This is determined by whether the current command index points to a valid position
     * within the history list, which means there is at least one executed command that can be undone.
     *
     * @return true if there is at least one command that can be undone, false otherwise.
     */
    public boolean canUndo() {
        return currentCommandIndex >= 0;
    }

    /**
     * Checks if there are commands available to redo.
     * This is determined by comparing the size of the command history list with the current command index.
     * If the size of the list is greater than the current command index + 1, it means there are undone commands
     * available to be redone.
     *
     * @return true if there is at least one command that can be redone, false otherwise.
     */
    public boolean canRedo() {
        return commandHistory.size() > currentCommandIndex + 1;
    }

    //=========== Undo and redo of add ======================================================================

    /**
     * Reverses the addition of a person to the address book.
     *
     * @param command The {@code AddCommand} whose effect is to be undone.
     */
    private void undoAdd(AddCommand command) {
        Person personAdded = command.getPersonToAdd();
        model.deletePerson(personAdded);
    }

    /**
     * Re-executes the addition of a person to the address book.
     *
     * @param command The {@code AddCommand} whose effect is to be redone.
     */
    private void redoAdd(AddCommand command) {
        Person personToAdd = command.getPersonToAdd();
        model.addPerson(personToAdd);
    }

    //=========== Undo and redo of delete ===================================================================

    /**
     * Reverses the deletion of a person from the address book.
     *
     * @param command The {@code DeleteCommand} whose effect is to be undone.
     */
    private void undoDelete(DeleteCommand command) {
        Person personDeleted = command.getPersonToDelete();
        model.addPerson(personDeleted);
    }

    /**
     * Re-executes the deletion of a person from the address book.
     *
     * @param command The {@code DeleteCommand} whose effect is to be redone.
     */
    private void redoDelete(DeleteCommand command) {
        Person personToDelete = command.getPersonToDelete();
        model.deletePerson(personToDelete);
    }

    //=========== Undo and redo of edit =====================================================================

    /**
     * Reverses the edit of a person in the address book.
     *
     * @param command The {@code EditCommand} whose effect is to be undone.
     */
    private void undoEdit(EditCommand command) {
        Person personToEdit = command.getPersonToEdit();
        Person editedPerson = command.getEditedPerson();
        model.deletePerson(editedPerson);
        model.addPerson(personToEdit);
    }

    /**
     * Re-executes the edit of a person in the address book.
     *
     * @param command The {@code EditCommand} whose effect is to be redone.
     */
    private void redoEdit(EditCommand command) {
        Person personToEdit = command.getPersonToEdit();
        Person editedPerson = command.getEditedPerson();
        model.deletePerson(personToEdit);
        model.addPerson(editedPerson);
    }

    //=========== Undo and redo of clear ====================================================================

    /**
     * Reverses the effect of a clear operation on the address book.
     * This method restores the state of the address book to what it was before the clear operation
     * was executed.
     *
     * @param command The {@code ClearCommand} whose clearing effect is to be undone.
     */
    private void undoClear(ClearCommand command) {
        ReadOnlyAddressBook addressBookToRestore = command.getAddressBookBeforeClear();
        model.setAddressBook(addressBookToRestore);
    }

    /**
     * Re-executes the clear operation on the address book.
     * After an undo operation has restored the address book to its previous state, this method
     * allows for the redo of the clear operation, effectively emptying the address book once again.
     *
     * @param command The {@code ClearCommand} that is to be redone, clearing the address book.
     */
    private void redoClear(ClearCommand command) {
        model.setAddressBook(new AddressBook());
    }

}
