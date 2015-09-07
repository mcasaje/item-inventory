package models.items.utils.sorting.tags;


import models.items.tags.Tag;

import java.util.Comparator;

class TagIdDescendingComparator implements Comparator<Tag> {

    @Override
    public int compare(Tag o1, Tag o2) {
        return o2.getId() - o1.getId();
    }
}
