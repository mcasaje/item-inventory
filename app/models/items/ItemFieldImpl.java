package models.items;

class ItemFieldImpl implements ItemField {

    private int id;
    private int itemId;
    private int fieldId;
    private String usernameOfOwner;
    private String value;

    ItemFieldImpl(int id, int itemId, int fieldId, String usernameOfOwner, String value) {
        this.id = id;
        this.itemId = itemId;
        this.fieldId = fieldId;
        this.usernameOfOwner = usernameOfOwner;
        this.value = value;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getItemId() {
        return itemId;
    }

    @Override
    public int getFieldId() {
        return fieldId;
    }

    @Override
    public String getUsername() {
        return usernameOfOwner;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(ItemField o) {
        String otherVal = o.getValue();
        return this.value.compareTo(otherVal);
    }
}
