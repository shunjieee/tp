package seedu.address.model.tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of valid tags for the addressbook.
 */
public class TagList {
    
    public List<Tag> tagList;

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
}
