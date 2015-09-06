package models.items;

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
    private String username;
    private final Set<Tag> tags;
    private final List<ItemField> itemFields;
    private final List<Rating> ratings;

    ItemImpl(int id, String name, String username, Set<Tag> tags, List<ItemField> itemFields, List<Rating> ratings) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.tags = tags;
        this.itemFields = itemFields;
        this.ratings = ratings;
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
    public List<Rating> getRatings() {
        return new ArrayList<>(ratings);
    }

    @Override
    public int countTags() {
        return tags == null ? 0 : tags.size();
    }

    @Override
    public int countFields() {
        return itemFields == null ? 0 : itemFields.size();
    }

    @Override
    public int countRatings() {
        return ratings == null ? 0 : ratings.size();
    }

    @Override
    public boolean hasTags() {
        return tags != null && tags.size() > 0;
    }

    @Override
    public boolean hasFields() {
        return itemFields != null && itemFields.size() > 0;
    }

    @Override
    public boolean hasRatings() {
        return ratings != null && ratings.size() > 0;
    }

    @Override
    public int compareTo(Item o) {

        int otherId = o.getId();

        if (this.id > otherId) {
            return 1;
        } else if (this.id < otherId) {
            return -1;
        } else {
            return 0;
        }

    }
}
