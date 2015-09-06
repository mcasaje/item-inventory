package models.items.fields;

/**
 * Immutable, a field is a customizable {@link String} attribute of an instance of {@link models.items.Item}
 * defined by the {@link models.items.types.ItemType}.
 */
public interface Field {

    int getId();

    String getName();

    int getItemTypeId();

    String getUsername();

}
