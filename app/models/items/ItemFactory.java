package models.items;

import models.items.itemfields.ItemField;
import models.items.tags.Tag;

import java.util.List;
import java.util.Set;

public interface ItemFactory {

    Item createItem(int id, String itemName, int itemTypeId, String username);

    Item createItem(int id, String itemName, int itemTypeId, String username, List<Tag> tags, List<ItemField> fields);

    Item createItem(Item item);

}
