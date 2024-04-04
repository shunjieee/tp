package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.tag.TagList;

/**
 * Represents a storage for {@link seedu.address.model.tag.TagList}.
 */
public interface TagListStorage {

    /**
     * Returns the file path of the TagList data file.
     */
    Path getTagListFilePath();

    /**
     * Returns TagList data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if the loading of data from preference file failed.
     */
    Optional<TagList> readTagList() throws DataLoadingException;

    /**
     * Saves the given {@link seedu.address.model.TagList} to the storage.
     * @param tagList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTagList(TagList tagList) throws IOException;

}
