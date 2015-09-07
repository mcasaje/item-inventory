package models.items.utils.sorting.items;

import models.items.Item;

import java.util.List;

public interface ItemSorter {

    void sortItems(List<Item> items, ItemSortStrategy sortStrategy);

    void sortItemsByItemFields(List<Item> items, int fieldId, ItemFieldSortStrategy sortStrategy);

}
