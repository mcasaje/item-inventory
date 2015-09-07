package models.items.utils.sorting.tags;

import models.items.Item;
import models.items.tags.Tag;

import java.util.Comparator;

class TagIdAscendingComparator implements Comparator<Tag> {

    @Override
    public int compare(Tag o1, Tag o2) {
        return o1.getId() - o2.getId();
    }
}
