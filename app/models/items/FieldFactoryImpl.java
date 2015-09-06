package models.items;

class FieldFactoryImpl implements FieldFactory {

    @Override
    public Field createField(int id, String fieldKey, String usernameOfOwner) {
        return new FieldImpl(id, fieldKey, usernameOfOwner);
    }

    @Override
    public Field createField(Field field) {
        int id = field.getId();
        String name = field.getName();
        String username = field.getUsername();
        return this.createField(id, name, username);
    }

    @Override
    public ItemField createItemField(int id, int itemId, int fieldId, String usernameOfOwner, String fieldValue) {
        return new ItemFieldImpl(id, itemId, fieldId, usernameOfOwner, fieldValue);
    }

    @Override
    public ItemField createItemField(ItemField itemField) {
        int id = itemField.getId();
        int itemId = itemField.getItemId();
        int fieldId = itemField.getFieldId();
        String username = itemField.getUsername();
        String value = itemField.getValue();
        return this.createItemField(id, itemId, fieldId, username, value);
    }
}
