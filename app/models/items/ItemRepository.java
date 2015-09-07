package models.items;

import javax.persistence.EntityManager;
import java.util.List;

public interface ItemRepository {

    Item findItem(EntityManager entityManager, int id);

    Item findItem(EntityManager entityManager, String name, String usernameOfOwner);

    List<Item> findItems(EntityManager entityManager, int itemTypeId, String usernameOfOwner);

    Item insertItem(EntityManager entityManager, Item item);

    void deleteItem(EntityManager entityManager, Item item);

}
