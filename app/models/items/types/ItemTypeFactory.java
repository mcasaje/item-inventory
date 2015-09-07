package models.items.types;

import models.items.fields.Field;
import models.items.tags.Tag;

import java.util.List;
import java.util.Set;

public interface ItemTypeFactory {

    ItemType createItemType(String name, String username);

    ItemType createItemType(int id, String name, String username, Set<Tag> tags, List<Field> fields);

}
