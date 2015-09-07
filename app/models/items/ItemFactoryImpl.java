package models.items;

import models.items.fields.Field;
import models.items.fields.FieldFactory;
import models.items.itemfields.ItemField;
import models.items.tags.Tag;
import models.items.tags.TagFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

/**
 * Creates instances of {@link Item}.
 * <p>
 * Delegates the creation (facade for creating) {@link Tag}, {@link Field} instances.
 */
class ItemFactoryImpl implements ItemFactory {

    private TagFactory tagFactory;
    private FieldFactory fieldFactory;

    @Inject
    ItemFactoryImpl(TagFactory tagFactory, FieldFactory fieldFactory) {
        this.tagFactory = tagFactory;
        this.fieldFactory = fieldFactory;
    }

    @Override
    public Item createItem(int id, String itemName, int itemTypeId, String username) {
        return this.createItem(id, itemName, itemTypeId, username, null, null);
    }

    @Override
    public Item createItem(int id, String itemName, int itemTypeId, String username, List<Tag> tags, List<ItemField> itemFields) {
        return new ItemImpl(id, itemName, itemTypeId, username, tags, itemFields);
    }

    @Override
    public Item createItem(Item item) {

        int id = item.getId();
        String name = item.getName();
        int itemTypeId = item.getItemTypeId();
        String username = item.getUsername();
        List<Tag> tags = item.getTags();
        List<ItemField> fields = item.getItemFields();

        return this.createItem(id, name, itemTypeId, username, tags, fields);
    }

}
