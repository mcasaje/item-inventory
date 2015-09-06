package models.items.fields;

public interface FieldFactory {

    Field createField(int id, String fieldKey, int itemTypeId, String usernameOfOwner);

    Field createField(Field field);

}
