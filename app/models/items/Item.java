package models.items;

import models.items.itemfields.ItemField;
import models.items.tags.Tag;

import java.util.List;
import java.util.Set;

/**
 * Immutable, represent a generic, customizable item.
 * <p>
 * The {@link #getId()} is used to compare two {@link Item}
 * objects which will allow arrays of {@link Item} objects to be sorted by their order of creation.
 */
public interface Item {

    int getId();

    String getName();

    int getItemTypeId();

    String getUsername();

    List<Tag> getTags();

    List<ItemField> getItemFields();

    int countTags();

    int countItemFields();

    boolean hasTags();

    boolean hasItemFields();

}
