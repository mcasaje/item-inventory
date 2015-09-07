package models.items.utils.sorting.items;

import models.items.Item;

import java.util.List;

class ItemSorterImpl implements ItemSorter {

    @Override
    public void sortItems(List<Item> items, ItemSortStrategy sortStrategy) {

        switch (sortStrategy) {

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

    @Override
    public void sortItemsByItemFields(List<Item> items, int fieldId, ItemFieldSortStrategy sortStrategy) {

        switch (sortStrategy) {

            case ITEM_FIELD_VALUE_ASC:
                items.sort(new ItemFieldValueAscendingComparator(fieldId));
                break;

            case ITEM_FIELD_VALUE_DESC:
                items.sort(new ItemFieldValueDescendingComparator(fieldId));
                break;

            default:
                items.sort(new ItemFieldValueAscendingComparator(fieldId));
                break;

        }

    }

}
