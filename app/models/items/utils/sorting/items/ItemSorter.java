package models.items.utils.sorting.items;

import models.items.Item;

import java.util.List;

public interface ItemSorter {

    void sortItems(List<Item> items, ItemSortStrategy itemSortStrategy);

}
