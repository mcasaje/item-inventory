package models.items.utils.sorting.items;

import models.items.Item;
import models.items.itemfields.ItemField;

import java.util.Comparator;

class ItemFieldValueDescendingComparator implements Comparator<Item> {

    private int fieldId;

    ItemFieldValueDescendingComparator(int fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public int compare(Item o1, Item o2) {

        ItemField itemField1 = o1.getItemField(fieldId);
        ItemField itemField2 = o2.getItemField(fieldId);

        if (itemField1 == null && itemField2 == null) {
            return 0;
        } else if (itemField1 == null) {
            return 1;
        } else if (itemField2 == null) {
            return -1;
        }

        String value1 = itemField1.getValue();
        String value2 = itemField2.getValue();

        return value2.compareTo(value1);

    }
}
