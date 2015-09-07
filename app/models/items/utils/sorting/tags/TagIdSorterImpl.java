package models.items.utils.sorting.tags;

import models.items.tags.Tag;

import java.util.List;

class TagIdSorterImpl implements TagSorter {

    @Override
    public void sortTags(List<Tag> tags, TagSortStrategy itemSortStrategy) {

        switch (itemSortStrategy) {

            case ID_ASC:
                tags.sort(new TagIdAscendingComparator());
                break;

            case ID_DESC:
                tags.sort(new TagIdDescendingComparator());
                break;

            case NAME_ASC:
                tags.sort(new TagNameAscendingComparator());
                break;

            case NAME_DESC:
                tags.sort(new TagNameDescendingComparator());
                break;

            default:
                tags.sort(new TagIdAscendingComparator());
                break;

        }

    }

}
