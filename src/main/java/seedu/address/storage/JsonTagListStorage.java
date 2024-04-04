package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.tag.TagList;

/**
 * A class to access TagList data stored as a json file on the hard disk.
 */
public class JsonTagListStorage implements TagListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTagListStorage.class);

    private Path filePath;

    public JsonTagListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTagListFilePath() {
        return filePath;
    }

    @Override
    public Optional<TagList> readTagList() throws DataLoadingException {
        return readTagList(filePath);
    }

    /**
     * Similar to {@link #readTagList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<TagList> readTagList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableTagList> jsonTagList = JsonUtil.readJsonFile(
                filePath, JsonSerializableTagList.class);
        if (!jsonTagList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTagList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveTagList(TagList TagList) throws IOException {
        saveTagList(TagList, filePath);
    }

    /**
     * Similar to {@link #saveTagList(ReadOnlyTagList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTagList(TagList TagList, Path filePath) throws IOException {
        requireNonNull(TagList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTagList(TagList), filePath);
    }

}
