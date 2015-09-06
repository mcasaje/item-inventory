package controllers.items;

import models.items.Item;
import models.items.utils.sorting.ItemSortStrategy;

import java.util.List;

/**
 * Provides methods to retrieve and sort instances of {@link models.items.Item}.
 */
public interface ItemsController {

    List<Item> getItems(String username, ItemSortStrategy sortStrategy);

}
