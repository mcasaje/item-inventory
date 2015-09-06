package models.items.types;

import models.items.fields.Field;

import java.util.List;

public interface ItemTypeFactory {

    ItemType createItemType(int id, String name, List<Field> fields);

}
