package controllers.items.fields;

import models.items.itemfields.ItemField;

public interface FieldsController {

    ItemField addFieldToItem(int itemId, int fieldId, String username, String fieldValue);

}
