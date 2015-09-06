package models.items.fields;

import javax.persistence.EntityManager;
import java.util.List;

public interface FieldRepository {

    Field findField(EntityManager entityManager, int id);

    Field findField(EntityManager entityManager, String name, String usernameOfOwner);

    List<Field> findFields(EntityManager entityManager, String usernameOfOwner);

    List<Field> findFields(EntityManager entityManager, int itemTypeId);

    Field insertField(EntityManager entityManager, Field field);

    void deleteField(EntityManager entityManager, Field field);

}
