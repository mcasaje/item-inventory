package models.items.types;

import models.items.fields.Field;

import java.util.ArrayList;
import java.util.List;

class ItemTypeImpl implements ItemType {

    private final int id;
    private final String name;
    private List<Field> fields;

    ItemTypeImpl(int id, String name, List<Field> fields) {
        this.id = id;
        this.name = name;
        this.fields = fields;
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
    public List<Field> getFields() {
        return new ArrayList<>(fields);
    }

    @Override
    public int countFields() {
        return fields == null ? 0 : fields.size();
    }

    @Override
    public boolean hasFields() {
        return fields != null && fields.size() > 0;
    }
}
