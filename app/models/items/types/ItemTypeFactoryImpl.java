package models.items.types;

import models.items.fields.Field;
import models.items.tags.Tag;

import java.util.List;

class ItemTypeFactoryImpl implements ItemTypeFactory {

    @Override
    public ItemType createItemType(String name, String username) {
        return new ItemTypeImpl(name, username);
    }

    @Override
    public ItemType createItemType(int id, String name, String username, List<Tag> tags, List<Field> fields) {
        return new ItemTypeImpl(id, name, username, tags, fields);
    }
}
