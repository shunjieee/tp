package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final TagList tagList;
    private final FilteredList<Person> filteredPersons;

    private final CommandList commandList;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, UserPrefs userPrefs, TagList tagList) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + ", user prefs " + userPrefs
                + " and tag list" + tagList);

        this.addressBook = new AddressBook(addressBook);
        this.commandList = new CommandList();
        this.commandList.linkToModel(this);
        this.userPrefs = userPrefs;
        this.tagList = tagList;
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new TagList());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefsIsSample(ReadAndWriteUserPrefs userPrefs, boolean status) {
        userPrefs.setIsSample(status);
    }

    public ReadAndWriteUserPrefs getReadAndWriteUserPrefs() {
        return userPrefs;
    }

    @Override
    public UserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    // ================ TagList ==============================

    @Override
    public TagList getTagList() {
        return tagList;
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireAllNonNull(tag);
        return tagList.hasTag(tag);
    }

    @Override
    public void addTag(Tag tag) {
        tagList.addTag(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        tagList.deleteTag(tag);
    }

    @Override
    public String listTags() {
        return tagList.listTags();
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

    //=========== Undo and redo feature ======================================================================

    @Override
    public void addExecutedCommand(Command command) {
        commandList.addCommand(command);
    }

    @Override
    public boolean canUndoCommand() {
        return commandList.canUndo();
    }

    @Override
    public boolean canRedoCommand() {
        return commandList.canRedo();
    }

    @Override
    public void undoCommand() {
        commandList.undo();
    }

    @Override
    public void redoCommand() {
        commandList.redo();
    }

}
