package controllers.items.types;

import models.items.fields.Field;
import models.items.tags.Tag;
import models.items.types.ItemType;

import java.util.List;

public interface ItemTypesController {

    boolean checkItemTypeExists(int itemTypeId);

    ItemType createItemType(String name, String username);

    ItemType getItemType(int itemTypeId);

    List<ItemType> getItemTypes(String username);

    Field addFieldToItemType(int itemTypeId, String username, String fieldName);

    Tag addTagToItemType(int itemTypeId, String username, String tagName);

}
