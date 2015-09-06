package controllers.items;

import models.items.Item;
import models.items.ItemRepository;
import models.items.utils.sorting.ItemSortStrategy;
import models.items.utils.sorting.ItemSorter;
import models.jpa.JPAUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

class ItemsControllerImpl implements ItemsController {

    private JPAUtils jpaUtils;
    private ItemRepository itemRepository;
    private ItemSorter itemSorter;

    @Inject
    ItemsControllerImpl(JPAUtils jpaUtils, ItemRepository itemRepository, ItemSorter itemSorter) {
        this.jpaUtils = jpaUtils;
        this.itemRepository = itemRepository;
        this.itemSorter = itemSorter;
    }

    @Override
    public List<Item> getItems(String username, ItemSortStrategy sortStrategy) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            List<Item> items = itemRepository.findItems(entityManager, username);
            itemSorter.sortItems(items, sortStrategy);

            entityManager.getTransaction().commit();

            return items;
        } finally {
            entityManager.close();
        }
    }
}
