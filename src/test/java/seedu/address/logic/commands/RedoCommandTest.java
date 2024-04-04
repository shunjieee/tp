package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.addTwoPeople;
// import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
// import static seedu.address.testutil.TypicalIds.ID_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
// import seedu.address.model.person.Person;
import seedu.address.model.tag.TagList;
// import seedu.address.testutil.EditPersonDescriptorBuilder;
// import seedu.address.testutil.PersonBuilder;

public class RedoCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TagList());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TagList());

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

    // @Test
    // public void redoAddTest() {
    //     try {
    //         new RedoCommand().execute(expectedModel);
    //     } catch (CommandException e) {
    //         // This branch will not be visited.
    //     }
    //     assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_SUCCESS, expectedModel);

    //     try {
    //         new RedoCommand().execute(expectedModel);
    //     } catch (CommandException e) {
    //         // This branch will not be visited.
    //     }
    //     assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_SUCCESS, expectedModel);

    //     assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_FAILURE);
    // }

    // @Test
    // public void redoEditTest() {
    //     Person editedPerson = new PersonBuilder().withId(ID_FIRST_PERSON.toString()).build();
    //     EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
    //     EditCommand editCommand = new EditCommand(ID_FIRST_PERSON, descriptor);

    //     try {
    //         editCommand.execute(model);
    //         editCommand.execute(expectedModel);
    //         new UndoCommand().execute(model);
    //         new UndoCommand().execute(expectedModel);
    //         new RedoCommand().execute(expectedModel);
    //     } catch (CommandException e) {
    //         // This branch will not be visited.
    //     }
    //     assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_SUCCESS, expectedModel);
    // }

    @Test
    public void redoClearTest() {
        new ClearCommand().execute(expectedModel);
        new ClearCommand().execute(model);

        try {
            new UndoCommand().execute(expectedModel);
            new UndoCommand().execute(model);
            new RedoCommand().execute(expectedModel);
        } catch (CommandException e) {
            // This branch will not be visited.
        }
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_SUCCESS, expectedModel);
    }
}
