package models.items;

import java.util.List;
import java.util.Set;

/**
 * Immutable, represent a generic, customizable item.
 * <p>
 * The {@link #getId()} is used to compare two {@link Item}
 * objects which will allow arrays of {@link Item} objects to be sorted by their order of creation.
 */
public interface Item extends Comparable<Item> {

    int getId();

    String getName();

    String getUsername();

    Set<Tag> getTags();

    List<ItemField> getItemFields();

    List<Rating> getRatings();

    int countTags();

    int countFields();

    int countRatings();

    boolean hasTags();

    boolean hasFields();

    boolean hasRatings();

}
