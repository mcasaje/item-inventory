package models.items.utils.sorting;

import models.items.Item;

import java.util.List;

class ItemIdSorterImpl implements ItemSorter {

    @Override
    public void sortItems(List<Item> items, ItemSortStrategy itemSortStrategy) {

        switch (itemSortStrategy) {

            case ID_ASC:
                items.sort(new ItemIdAscendingComparator());
                break;

            case ID_DESC:
                items.sort(new ItemIdDescendingComparator());
                break;

            case NAME_ASC:
                items.sort(new ItemNameAscendingComparator());
                break;

            case NAME_DESC:
                items.sort(new ItemNameDescendingComparator());
                break;

            default:
                items.sort(new ItemIdAscendingComparator());
                break;

        }

    }

}
