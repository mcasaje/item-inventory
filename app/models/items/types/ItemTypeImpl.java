package models.items.types;

import models.items.fields.Field;

import java.util.ArrayList;
import java.util.List;

class ItemTypeImpl implements ItemType {

    private final Integer id;
    private final String name;
    private final String username;
    private final List<Field> fields;

    ItemTypeImpl(String name, String username) {
        this.id = null;
        this.name = name;
        this.username = username;
        this.fields = null;
    }

    ItemTypeImpl(int id, String name, String username, List<Field> fields) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.fields = fields;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsername() {
        return username;
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
