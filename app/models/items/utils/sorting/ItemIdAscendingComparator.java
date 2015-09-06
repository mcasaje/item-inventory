package models.items.utils.sorting;

import models.items.Item;

import java.util.Comparator;

class ItemIdAscendingComparator implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        return o1.getId() - o2.getId();
    }
}
