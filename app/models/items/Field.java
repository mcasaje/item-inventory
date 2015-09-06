package models.items;

/**
 * Immutable, a field is a customizable {@link String} attribute of an instance of {@link models.items.Item}
 */
public interface Field extends Comparable<Field> {

    int getId();

    String getName();

    String getUsername();

}
