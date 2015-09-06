package models.items;

import java.util.List;

public interface FieldRepository {

    Field findField(int id);

    Field findField(String name, String usernameOfOwner);

    List<Field> findFields(String usernameOfOwner);

    Field insertField(Field field);

    void deleteField(Field field);

    ItemField findItemField(int id);

    List<ItemField> findItemFields(int itemId);

    ItemField insertItemField(ItemField field);

    void deleteItemField(ItemField field);

}
