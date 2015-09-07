package controllers.items;

import models.items.Item;
import models.items.ItemRepository;
import models.items.utils.sorting.items.ItemSortStrategy;
import models.items.utils.sorting.items.ItemSorter;
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
    public Item createItem(String name, int itemTypeId, String username) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            Item item = itemRepository.insertItem(entityManager, name, itemTypeId, username);
            entityManager.getTransaction().commit();

            return item;

        } finally {
            entityManager.close();
        }
    }

    @Override
    public Item getItem(int itemId, String username) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            Item item = itemRepository.findItem(entityManager, itemId, username);
            entityManager.getTransaction().commit();

            return item;

        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Item> getItems(int itemTypeId, String username, ItemSortStrategy sortStrategy) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            List<Item> items = itemRepository.findItems(entityManager, itemTypeId, username);
            itemSorter.sortItems(items, sortStrategy);

            entityManager.getTransaction().commit();

            return items;
        } finally {
            entityManager.close();
        }
    }
}
