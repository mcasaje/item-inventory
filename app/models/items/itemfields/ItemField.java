package models.items.itemfields;

import models.items.Item;
import models.items.fields.Field;

/**
 * Immutable, this represents a {@link Field} that has been attached to an {@link Item} that may contain a user-entered value.
 */
public interface ItemField {

    int getId();

    int getItemId();

    int getFieldId();

    String getUsername();

    String getValue();

}
