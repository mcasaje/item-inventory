package models.items.types;

import models.items.fields.Field;

import java.util.List;

class ItemTypeFactoryImpl implements ItemTypeFactory {

    @Override
    public ItemType createItemType(String name, String username) {
        return new ItemTypeImpl(name, username);
    }

    @Override
    public ItemType createItemType(int id, String name, String username, List<Field> fields) {
        return new ItemTypeImpl(id, name, username, fields);
    }
}
