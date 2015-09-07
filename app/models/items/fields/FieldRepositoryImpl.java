package models.items.fields;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

class FieldRepositoryImpl implements FieldRepository {

    private final FieldFactory fieldFactory;

    @Inject
    FieldRepositoryImpl(FieldFactory fieldFactory) {
        this.fieldFactory = fieldFactory;
    }

    @Override
    public Field findField(EntityManager entityManager, int id) {

        final String queryString = "SELECT o FROM FieldDAO o WHERE o.id=:ID";
        TypedQuery<FieldDAO> query = entityManager.createQuery(queryString, FieldDAO.class);
        query.setParameter("ID", id);
        FieldDAO dao = query.getSingleResult();
        String name = dao.getName();
        int itemTypeId = dao.getItemTypeId();
        String usernameOfOwner = dao.getUsernameOfOwner();

        assert id == dao.getId();

        return fieldFactory.createField(id, name, itemTypeId, usernameOfOwner);
    }

    @Override
    public Field findField(EntityManager entityManager, String name, String usernameOfOwner) {

        final String queryString = "SELECT o FROM FieldDAO o WHERE o.name=:NAME AND o.usernameOfOwner=:USERNAME_OF_OWNER";
        TypedQuery<FieldDAO> query = entityManager.createQuery(queryString, FieldDAO.class);
        query.setParameter("NAME", name);
        query.setParameter("USERNAME_OF_OWNER", usernameOfOwner);
        FieldDAO dao = query.getSingleResult();

        int id = dao.getId();
        int itemTypeId = dao.getItemTypeId();

        assert name.equals(dao.getName());
        assert usernameOfOwner.equals(dao.getUsernameOfOwner());

        return fieldFactory.createField(id, name, itemTypeId, usernameOfOwner);
    }

    @Override
    public List<Field> findFields(EntityManager entityManager, String usernameOfOwner) {

        final String queryString = "SELECT o FROM FieldDAO o WHERE o.usernameOfOwner=:USERNAME_OF_OWNER";
        TypedQuery<FieldDAO> query = entityManager.createQuery(queryString, FieldDAO.class);
        query.setParameter("USERNAME_OF_OWNER", usernameOfOwner);
        List<FieldDAO> daoList = query.getResultList();

        ArrayList<Field> fields = new ArrayList<>();

        for (FieldDAO dao : daoList) {
            int id = dao.getId();
            String name = dao.getName();
            int itemTypeId = dao.getItemTypeId();

            assert usernameOfOwner.equals(dao.getUsernameOfOwner());

            Field field = fieldFactory.createField(id, name, itemTypeId, usernameOfOwner);

            fields.add(field);
        }

        return fields;
    }

    @Override
    public List<Field> findFields(EntityManager entityManager, int itemTypeId) {

        final String queryString = "SELECT o FROM FieldDAO o WHERE o.itemTypeId=:ITEM_TYPE_ID";
        TypedQuery<FieldDAO> query = entityManager.createQuery(queryString, FieldDAO.class);
        query.setParameter("ITEM_TYPE_ID", itemTypeId);
        List<FieldDAO> daoList = query.getResultList();

        ArrayList<Field> fields = new ArrayList<>();

        for (FieldDAO dao : daoList) {
            int id = dao.getId();
            String name = dao.getName();
            String usernameOfOwner = dao.getUsernameOfOwner();

            assert itemTypeId == dao.getItemTypeId();

            Field field = fieldFactory.createField(id, name, itemTypeId, usernameOfOwner);

            fields.add(field);
        }

        return fields;
    }

    @Override
    public Field insertField(EntityManager entityManager, String name, int itemTypeId, String usernameOfOwner) {

        FieldDAO dao = new FieldDAO();
        dao.setName(name);
        dao.setItemTypeId(itemTypeId);
        dao.setUsernameOfOwner(usernameOfOwner);

        entityManager.persist(dao);

        int id = dao.getId();

        return fieldFactory.createField(id, name, itemTypeId, usernameOfOwner);
    }

    @Override
    public void deleteField(EntityManager entityManager, Field field) {

        FieldDAO dao = this.createFieldDAO(field);

        entityManager.merge(dao);
        entityManager.remove(dao);
    }

    /*
        A helper function to create the Field DAO.
     */
    private FieldDAO createFieldDAO(Field field) {

        int id = field.getId();
        String name = field.getName();
        String username = field.getUsername();

        FieldDAO dao = new FieldDAO();
        dao.setId(id);
        dao.setName(name);
        dao.setUsernameOfOwner(username);

        return dao;
    }


}
