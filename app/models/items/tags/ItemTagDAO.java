package models.items.tags;

import javax.persistence.*;

@Entity
@Table(name = "item_tag")
class ItemTagDAO {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "tag_id")
    private int tagId;

    @Column(name = "username")
    private String usernameOfOwner;

    int getId() {
        return id;
    }

    int getItemId() {
        return itemId;
    }

    int getTagId() {
        return tagId;
    }

    String getUsernameOfOwner() {
        return usernameOfOwner;
    }

    void setId(int id) {
        this.id = id;
    }

    void setItemId(int itemId) {
        this.itemId = itemId;
    }

    void setTagId(int tagId) {
        this.tagId = tagId;
    }

    void setUsernameOfOwner(String usernameOfOwner) {
        this.usernameOfOwner = usernameOfOwner;
    }
}
