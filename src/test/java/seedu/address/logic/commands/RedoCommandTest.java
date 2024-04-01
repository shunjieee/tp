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

public class RedoCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        addTwoPeople(model);
        try {
            new UndoCommand().execute(model);
            new UndoCommand().execute(model);
        } catch (CommandException e) {
            // This branch will not be visited.
        }

        addTwoPeople(expectedModel);
        try {
            new UndoCommand().execute(expectedModel);
            new UndoCommand().execute(expectedModel);
        } catch (CommandException e) {
            // This branch will not be visited.
        }
    }

    @Test
    public void execute() {
        try {
            new RedoCommand().execute(expectedModel);
        } catch (CommandException e) {
            // This branch will not be visited.
        }
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_SUCCESS, expectedModel);

        try {
            new RedoCommand().execute(expectedModel);
        } catch (CommandException e) {
            // This branch will not be visited.
        }
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_SUCCESS, expectedModel);

        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_FAILURE);
    }
}
