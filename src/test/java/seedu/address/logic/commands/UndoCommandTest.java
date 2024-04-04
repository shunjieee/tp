package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.addTwoPeople;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class UndoCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        addTwoPeople(model);

        addTwoPeople(expectedModel);
    }

    @Test
    public void undoAddTest() {
        try {
            new UndoCommand().execute(expectedModel);
        } catch (CommandException e) {
            // This branch will not be visited.
        }
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);

        try {
            new UndoCommand().execute(expectedModel);
        } catch (CommandException e) {
            // This branch will not be visited.
        }
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);

        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_FAILURE);
    }

    @Test
    public void undoClearTest() {
        new ClearCommand().execute(expectedModel);
        new ClearCommand().execute(model);

        try {
            new UndoCommand().execute(expectedModel);
        } catch (CommandException e) {
            // This branch will not be visited.
        }
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);
    }

    @Test
    public void undoEditTest() {
        Person editedPerson = new PersonBuilder().withId("1234").build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(new Id("1234"), descriptor);

        try {
            editCommand.execute(model);
            editCommand.execute(expectedModel);
            new UndoCommand().execute(expectedModel);
        } catch (CommandException e) {
            // This branch will not be visited.
        }
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);
    }
}
