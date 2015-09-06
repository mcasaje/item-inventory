package models.items.fields;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "field")
class FieldDAO {

    @Id
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

    int getItemTypeId() {
        return itemTypeId;
    }

    String getName() {
        return name;
    }

    String getUsernameOfOwner() {
        return usernameOfOwner;
    }

    void setItemTypeId(int itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    void setId(int id) {
        this.id = id;
    }

    void setName(String name) {
        this.name = name;
    }

    void setUsernameOfOwner(String usernameOfOwner) {
        this.usernameOfOwner = usernameOfOwner;
    }
}
