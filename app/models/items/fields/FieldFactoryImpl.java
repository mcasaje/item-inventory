package models.items.fields;

class FieldFactoryImpl implements FieldFactory {

    @Override
    public Field createField(int id, String fieldKey, int itemTypeId, String usernameOfOwner) {
        return new FieldImpl(id, fieldKey, itemTypeId, usernameOfOwner);
    }

    @Override
    public Field createField(Field field) {
        int id = field.getId();
        String name = field.getName();
        int itemTypeId = field.getItemTypeId();
        String username = field.getUsername();
        return this.createField(id, name, itemTypeId, username);
    }

}
