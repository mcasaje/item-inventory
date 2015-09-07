package models.items.itemfields;

import javax.persistence.*;

@Entity
@Table(name = "item_field")
class ItemFieldDAO {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "field_id")
    private int fieldId;

    @Column(name = "username")
    private String usernameOfOwner;

    @Column(name = "field_val")
    private String value;

    int getId() {
        return id;
    }

    int getItemId() {
        return itemId;
    }

    int getFieldId() {
        return fieldId;
    }

    String getUsernameOfOwner() {
        return usernameOfOwner;
    }

    String getValue() {
        return value;
    }

    void setId(int id) {
        this.id = id;
    }

    void setItemId(int itemId) {
        this.itemId = itemId;
    }

    void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    void setUsernameOfOwner(String usernameOfOwner) {
        this.usernameOfOwner = usernameOfOwner;
    }

    void setValue(String value) {
        this.value = value;
    }
}
