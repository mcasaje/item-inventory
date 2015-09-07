package models.items.utils.sorting.items;

import models.items.Item;

import java.util.Comparator;

class ItemIdDescendingComparator implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        return o2.getId() - o1.getId();
    }
}
