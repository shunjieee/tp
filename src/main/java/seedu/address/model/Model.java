package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.Command;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    //=========== UserPrefs ==================================================================================

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefsIsSample(ReadAndWriteUserPrefs userPrefs, boolean status);

    /**
     * Returns user prefs.
     */
    UserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    //=========== TagList ================================================================================

    /**
     * Returns the instance of tag list.
     */
    TagList getTagList();

    /**
     * Returns true if a tag exists in the tag list.
     */
    boolean hasTag(Tag tag);

    /**
     * Adds the given person.
     * {@code Tag} must not already exist in the tag list.
     */
    void addTag(Tag tag);

    /**
     * Deletes the given tag.
     * The tag must exist in the tag list.
     */
    void deleteTag(Tag tag);

    /**
     * List the tags in the tag list.
     */
    String listTags();

    //=========== AddressBook ================================================================================

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoCommand();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoCommand();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoCommand();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoCommand();

    /**
     * Adds the specified command to the command history list.
     * This method is intended to track commands that have been executed,
     * allowing for potential undo or redo actions.
     *
     * @param command The command that has been executed and is to be added to the history.
     */
    void addExecutedCommand(Command command);
}
