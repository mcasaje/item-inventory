package controllers.items;

import models.items.Item;
import models.items.ItemRepository;
import models.items.tags.Tag;
import models.items.utils.sorting.items.ItemFieldSortStrategy;
import models.items.utils.sorting.items.ItemSortStrategy;
import models.items.utils.sorting.items.ItemSorter;
import models.jpa.JPAUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
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

    @Override
    public List<Item> getItems(int itemTypeId, String username, ItemSortStrategy sortStrategy, int tagId) {

        List<Item> items = this.getItems(itemTypeId, username, sortStrategy);

        ArrayList<Item> filteredItems = new ArrayList<>();

        for (Item i : items) {

            Tag tag = i.getTag(tagId);

            if (tag != null) {
                filteredItems.add(i);
            }

        }

        return filteredItems;

    }

    @Override
    public List<Item> sortItemByField(List<Item> items, int fieldId, ItemFieldSortStrategy sortStrategy) {
        itemSorter.sortItemsByItemFields(items, fieldId, sortStrategy);
        return items;
    }

    @Override
    public void deleteItem(int itemId, String username) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            itemRepository.deleteItem(entityManager, itemId, username);
            entityManager.getTransaction().commit();

        } finally {
            entityManager.close();
        }
    }
}
