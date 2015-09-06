package models.items.tags;

/**
 * Immutable, a tag is a customizable enumeration used to sort {@link models.items.Item} objects.
 */
public interface Tag {

    int getId();

    String getName();

    String getUsername();

}
