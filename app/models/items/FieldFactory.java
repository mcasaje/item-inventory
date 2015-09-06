package models.items;

public interface FieldFactory {

    Field createField(int id, String fieldKey, String fieldValue);

    Field createField(Field field);

}
