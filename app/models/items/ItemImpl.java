package models.items;

import models.items.itemfields.ItemField;
import models.items.tags.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Immutable
 */
class ItemImpl implements Item {

    private final int id;
    private final String name;
    private int itemTypeId;
    private String username;
    private final Set<Tag> tags;
    private final List<ItemField> itemFields;

    ItemImpl(int id, String name, int itemTypeId, String username, Set<Tag> tags, List<ItemField> itemFields) {
        this.id = id;
        this.name = name;
        this.itemTypeId = itemTypeId;
        this.username = username;
        this.tags = tags;
        this.itemFields = itemFields;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getItemTypeId() {
        return itemTypeId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Set<Tag> getTags() {
        return new HashSet<>(tags);
    }

    @Override
    public List<ItemField> getItemFields() {
        return new ArrayList<>(itemFields);
    }

    @Override
    public int countTags() {
        return tags == null ? 0 : tags.size();
    }

    @Override
    public int countItemFields() {
        return itemFields == null ? 0 : itemFields.size();
    }

    @Override
    public boolean hasTags() {
        return tags != null && tags.size() > 0;
    }

    @Override
    public boolean hasItemFields() {
        return itemFields != null && itemFields.size() > 0;
    }

}
