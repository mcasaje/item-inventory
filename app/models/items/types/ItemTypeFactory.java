package models.items.types;

import models.items.fields.Field;

import java.util.List;

public interface ItemTypeFactory {

    ItemType createItemType(String name, String username);

    ItemType createItemType(int id, String name, String username, List<Field> fields);

}
