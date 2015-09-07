package models.items.utils.sorting.tags;


import models.items.tags.Tag;

import java.util.Comparator;

class TagNameAscendingComparator implements Comparator<Tag> {

    @Override
    public int compare(Tag o1, Tag o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
