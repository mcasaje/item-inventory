package models.items;

import javax.persistence.*;

@Entity
@Table(name = "item")
class ItemDAO {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "item_type_id")
    private int itemTypeId;

    @Column(name = "username")
    private String usernameOfOwner;

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    int getItemTypeId() {
        return itemTypeId;
    }

    String getUsernameOfOwner() {
        return usernameOfOwner;
    }

    void setId(int id) {
        this.id = id;
    }

    void setName(String name) {
        this.name = name;
    }

    void setItemTypeId(int itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    void setUsernameOfOwner(String usernameOfOwner) {
        this.usernameOfOwner = usernameOfOwner;
    }
}
