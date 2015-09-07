package models.items.utils.sorting.tags;

import models.items.tags.Tag;

import java.util.List;

public interface TagSorter {

    void sortTags(List<Tag> tags, TagSortStrategy itemSortStrategy);

}
