package models.items.types;

import models.items.Item;
import models.items.fields.Field;
import models.items.tags.Tag;

import java.util.List;
import java.util.Set;

/**
 * Represents the blueprint of how to describe an instance of {@link Item}.
 * <p>
 * For example: An {@link Item} that is meant to represent person would have a corresponding {@link ItemType} that may
 * have {@link Field} objects representing first name, last name, email, and phone number.
 */
public interface ItemType {

    Integer getId();

    String getName();

    String getUsername();

    Set<Tag> getTags();

    List<Field> getFields();

    int countTags();

    int countFields();

    boolean hasTags();

    boolean hasFields();
}
