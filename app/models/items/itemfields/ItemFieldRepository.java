package models.items.itemfields;

import javax.persistence.EntityManager;
import java.util.List;

public interface ItemFieldRepository {

    ItemField findItemField(EntityManager entityManager, int id);

    List<ItemField> findItemFields(EntityManager entityManager, int itemId);

    ItemField insertItemField(EntityManager entityManager, ItemField field);

    void deleteItemField(EntityManager entityManager, ItemField field);

}
