package models.items;

public interface FieldFactory {

    Field createField(int id, String fieldKey, String usernameOfOwner);

    Field createField(Field field);

    ItemField createItemField(int id, int itemId, int fieldId, String usernameOfOwner, String fieldValue);

    ItemField createItemField(ItemField itemField);

}
