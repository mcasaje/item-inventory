package models.items.types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item_type")
class ItemTypeDAO {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String usernameOfOwner;

    int getId() {
        return id;
    }

    String getName() {
        return name;
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

    void setUsernameOfOwner(String usernameOfOwner) {
        this.usernameOfOwner = usernameOfOwner;
    }
}
