package models.items;

import models.items.itemfields.ItemField;
import models.items.tags.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Immutable
 */
class ItemImpl implements Item {

    private final int id;
    private final String name;
    private final int itemTypeId;
    private final String username;
    private final List<Tag> tags;
    private final List<ItemField> itemFields;

    // Key: Tag ID, Val: Tag
    private HashMap<Integer, Tag> tagMap = new HashMap<>();

    // Key: Field ID, Val: ItemField
    private HashMap<Integer, ItemField> itemFieldMap = new HashMap<>();

    ItemImpl(int id, String name, int itemTypeId, String username, List<Tag> tags, List<ItemField> itemFields) {
        this.id = id;
        this.name = name;
        this.itemTypeId = itemTypeId;
        this.username = username;
        this.tags = tags;
        this.itemFields = itemFields;

        buildTagMap(tags);
        buildItemFieldMap(itemFields);
    }

    /*
        Builds a map with Tag IDs as the key and Tags as the value.
     */
    private void buildTagMap(List<Tag> tags) {

        if (tags != null) {

            HashMap<Integer, Tag> tagMap = new HashMap<>();

            for (Tag t : tags) {
                int id = t.getId();
                tagMap.put(id, t);
            }

            this.tagMap = tagMap;

        }
    }

    /*
        Builds a map with Tag IDs as the key and Tags as the value.
    */
    private void buildItemFieldMap(List<ItemField> itemFields) {

        if (tags != null) {

            HashMap<Integer, ItemField> itemFieldMap = new HashMap<>();

            for (ItemField f : itemFields) {
                int id = f.getFieldId();
                itemFieldMap.put(id, f);
            }

            this.itemFieldMap = itemFieldMap;

        }
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
    public List<Tag> getTags() {
        return new ArrayList<>(tags);
    }

    @Override
    public List<ItemField> getItemFields() {
        return new ArrayList<>(itemFields);
    }

    @Override
    public Tag getTag(int tagId) {
        return tagMap.get(tagId);
    }

    @Override
    public ItemField getItemField(int fieldId) {
        return itemFieldMap.get(fieldId);
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
