package models.items;

import java.util.List;
import java.util.Set;

public interface ItemFactory extends TagFactory, FieldFactory {

    Item createItem(int id, String itemName, String username);

    Item createItem(int id, String itemName, String username, Set<Tag> tags, List<Field> fields, List<Rating> ratings);

    Item createItem(Item item);

    Rating createRating(int id, String ratingKey, int ratingValue);

    Rating createRating(Rating rating);

}
