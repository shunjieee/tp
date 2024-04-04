package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.TagList;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage, TagListStorage {

    // ================ UserPrefs methods ==============================

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    // ================ AddressBook methods ==============================

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    // ================ TagList methods ==============================

    @Override
    Path getTagListFilePath();

    @Override
    Optional<TagList> readTagList() throws DataLoadingException;

    @Override
    void saveTagList(TagList TagList) throws IOException;

}
