package models.items.utils.sorting.items;

import models.items.Item;

import java.util.Comparator;

class ItemNameDescendingComparator implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        return o2.getName().compareTo(o1.getName());
    }
}
