package models.items;

/**
 * Immutable, a tag is a customizable enumeration used to sort {@link models.items.Item} objects.
 */
public interface Tag extends Comparable<Tag> {

    int getId();

    String getName();

    String getUsername();

}
