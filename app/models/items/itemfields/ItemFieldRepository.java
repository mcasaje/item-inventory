package models.items.itemfields;

import models.items.Item;

import javax.persistence.EntityManager;
import java.util.List;

public interface ItemFieldRepository {

    ItemField findItemField(EntityManager entityManager, int id);

    List<ItemField> findItemFields(EntityManager entityManager, int itemId);

    ItemField insertItemField(EntityManager entityManager, int itemId, int fieldId, String username, String fieldValue);

    void deleteItemField(EntityManager entityManager, Item item);

}
