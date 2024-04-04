package seedu.address.model.tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of valid tags for the addressbook.
 */
public class TagList {

    private List<Tag> tagList;

    /**
     * Constructs a blank {@code TagList}.
     */
    public TagList() {
        tagList = new ArrayList<>();
    }

    /**
     * Returns the tag list.
     */
    public List<Tag> getTagList() {
        return tagList;
    }

    /**
     * Returns true if a tag with the same name as {@code tag} exists.
     */
    public boolean hasTag(Tag tag) {
        return tagList.contains(tag);
    }

    /**
     * Adds a tag to the tag list.
     * The tag must not already exist in the tag list.
     */
    public void addTag(Tag tag) {
        tagList.add(tag);
    }

    /**
     * Deletes the given tag.
     * The tag must exist in the tag list.
     */
    public void deleteTag(Tag tag) {
        tagList.remove(tag);
    }

    /**
     * List the tags in the tag list.
     */
    public String listTags() {
        StringBuilder sb = new StringBuilder();
        for (Tag tag : tagList) {
            sb.append(tag.tagName);
            sb.append("\n");
        }
        return sb.toString();
    }
}
