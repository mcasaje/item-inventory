package models.items.itemfields;

public interface ItemFieldFactory {

    ItemField createItemField(int id, int itemId, int fieldId, String username, String value);

    ItemField createItemField(ItemField itemField);
}
