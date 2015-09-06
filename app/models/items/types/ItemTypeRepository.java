package models.items.types;

import javax.persistence.EntityManager;
import java.util.List;

public interface ItemTypeRepository {

    ItemType findItemType(EntityManager entityManager, int id);

    List<ItemType> findItemTypes(EntityManager entityManager, String username);

}
