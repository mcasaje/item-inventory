package models.items;

import models.items.itemfields.ItemField;
import models.items.tags.Tag;

import java.util.List;

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

    Tag getTag(int tagId);

    /**
     * Retrieves the {@link ItemField} with the corresponding {@link models.items.fields.Field} id.
     *
     * @param fieldId The {@link models.items.fields.Field} id corresponding to {@link ItemField#getFieldId()}.
     * @return
     */
    ItemField getItemField(int fieldId);

    int countTags();

    int countItemFields();

    boolean hasTags();

    boolean hasItemFields();

}
