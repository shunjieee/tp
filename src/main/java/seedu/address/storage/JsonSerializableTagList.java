package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagList;

/**
 * An Immutable TagList that is serializable to JSON format.
 */
@JsonRootName(value = "TagList")
class JsonSerializableTagList {

    public static final String MESSAGE_DUPLICATE_TAG = "Tag list contains duplicate tag(s).";

    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTagList} with the given tags.
     */
    @JsonCreator
    public JsonSerializableTagList(@JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.tags.addAll(tags);
    }

    /**
     * Converts a given {@code TagList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTagList}.
     */
    public JsonSerializableTagList(TagList source) {
        tags.addAll(source.getTagList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this tag list into the model's {@code TagList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TagList toModelType() throws IllegalValueException {
        TagList TagList = new TagList();
        for (JsonAdaptedTag jsonAdaptedTag : tags) {
            Tag tag = jsonAdaptedTag.toModelType();
            if (TagList.hasTag(tag)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TAG);
            }
            TagList.addTag(tag);
        }
        return TagList;
    }

}
