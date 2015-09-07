package models.items;

import javax.persistence.EntityManager;
import java.util.List;

public interface ItemRepository {

    Item findItem(EntityManager entityManager, int id, String usernameOfOwner);

    Item findItem(EntityManager entityManager, String name, String usernameOfOwner);

    List<Item> findItems(EntityManager entityManager, int itemTypeId, String usernameOfOwner);

    Item insertItem(EntityManager entityManager, String name, int itemTypeId, String usernameOfOwner);

    void deleteItem(EntityManager entityManager, Item item);

}
