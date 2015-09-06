package models.items;

import java.util.List;

public interface ItemRepository extends TagRepository, FieldRepository {

    Item findItem(int id);

    Item findItem(String name, String usernameOfOwner);

    List<Item> findItems(String usernameOfOwner);

    Item insertItem(Item item);

    void deleteItem(Item item);

}
