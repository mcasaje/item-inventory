package models.items.itemfields;

class ItemFieldFactoryImpl implements ItemFieldFactory {

    @Override
    public ItemField createItemField(int id, int itemId, int fieldId, String username, String value) {
        return new ItemFieldImpl(id, itemId, fieldId, username, value);
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
