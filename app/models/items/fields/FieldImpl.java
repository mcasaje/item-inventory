package models.items.fields;

class FieldImpl implements Field {

    private int id;
    private String name;
    private int itemTypeId;
    private String usernameOfOwner;

    FieldImpl(int id, String name, int itemTypeId, String usernameOfOwner) {
        this.id = id;
        this.name = name;
        this.itemTypeId = itemTypeId;
        this.usernameOfOwner = usernameOfOwner;
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
        return usernameOfOwner;
    }

}
