package controllers.items.types;

import models.items.fields.Field;
import models.items.types.ItemType;

public interface ItemTypesController {

    boolean checkItemTypeExists(int itemTypeId);

    ItemType createItemType(String name, String username);

    Field addFieldToItemType(int itemTypeId, String username, String fieldName);

}
