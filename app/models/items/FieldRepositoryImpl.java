package models.items;

import models.jpa.JPAUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

class FieldRepositoryImpl implements FieldRepository {

    private final JPAUtils jpaUtils;
    private final FieldFactory fieldFactory;

    @Inject
    FieldRepositoryImpl(JPAUtils jpaUtils, FieldFactory fieldFactory) {
        this.jpaUtils = jpaUtils;
        this.fieldFactory = fieldFactory;
    }

    @Override
    public Field findField(int id) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            final String queryString = "SELECT o FROM FieldDAO o WHERE o.id=:ID";
            TypedQuery<FieldDAO> query = entityManager.createQuery(queryString, FieldDAO.class);
            query.setParameter("ID", id);
            FieldDAO dao = query.getSingleResult();
            String name = dao.getName();
            String usernameOfOwner = dao.getUsernameOfOwner();

            assert id == dao.getId();

            entityManager.getTransaction().commit();

            return fieldFactory.createField(id, name, usernameOfOwner);

        } finally {
            entityManager.close();
        }
    }

    @Override
    public Field findField(String name, String usernameOfOwner) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            final String queryString = "SELECT o FROM FieldDAO o WHERE o.name=:NAME AND o.usernameOfOwner=:USERNAME_OF_OWNER";
            TypedQuery<FieldDAO> query = entityManager.createQuery(queryString, FieldDAO.class);
            query.setParameter("NAME", name);
            query.setParameter("USERNAME_OF_OWNER", usernameOfOwner);
            FieldDAO dao = query.getSingleResult();
            int id = dao.getId();

            assert name.equals(dao.getName());
            assert usernameOfOwner.equals(dao.getUsernameOfOwner());

            entityManager.getTransaction().commit();

            return fieldFactory.createField(id, name, usernameOfOwner);

        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Field> findFields(String usernameOfOwner) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            final String queryString = "SELECT o FROM FieldDAO o WHERE o.usernameOfOwner=:USERNAME_OF_OWNER";
            TypedQuery<FieldDAO> query = entityManager.createQuery(queryString, FieldDAO.class);
            query.setParameter("USERNAME_OF_OWNER", usernameOfOwner);
            List<FieldDAO> daoList = query.getResultList();

            ArrayList<Field> fields = new ArrayList<>();

            for (FieldDAO dao : daoList) {
                int id = dao.getId();
                String name = dao.getName();

                assert usernameOfOwner.equals(dao.getUsernameOfOwner());

                Field field = fieldFactory.createField(id, name, usernameOfOwner);

                fields.add(field);
            }

            entityManager.getTransaction().commit();

            return fields;

        } finally {
            entityManager.close();
        }
    }

    @Override
    public Field insertField(Field field) {

        FieldDAO dao = this.createFieldDAO(field);

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            entityManager.persist(dao);
            entityManager.getTransaction().commit();

            int id = dao.getId();
            String name = dao.getName();
            String usernameOfOwner = dao.getUsernameOfOwner();

            return fieldFactory.createField(id, name, usernameOfOwner);

        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteField(Field field) {

        FieldDAO dao = this.createFieldDAO(field);

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            entityManager.merge(dao);
            entityManager.remove(dao);
            entityManager.getTransaction().commit();

        } finally {
            entityManager.close();
        }
    }

    @Override
    public ItemField findItemField(int id) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            final String queryString = "SELECT o FROM ItemFieldDAO o WHERE o.id=:ID";
            TypedQuery<ItemFieldDAO> query = entityManager.createQuery(queryString, ItemFieldDAO.class);
            query.setParameter("ID", id);
            ItemFieldDAO dao = query.getSingleResult();
            int itemId = dao.getItemId();
            int fieldId = dao.getFieldId();
            String usernameOfOwner = dao.getUsernameOfOwner();
            String value = dao.getValue();

            assert id == dao.getId();

            entityManager.getTransaction().commit();

            return fieldFactory.createItemField(id, itemId, fieldId, usernameOfOwner, value);

        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<ItemField> findItemFields(int itemId) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            final String queryString = "SELECT o FROM ItemFieldDAO o WHERE o.itemId=:ITEM_ID";
            TypedQuery<ItemFieldDAO> query = entityManager.createQuery(queryString, ItemFieldDAO.class);
            query.setParameter("ITEM_ID", itemId);
            List<ItemFieldDAO> daoList = query.getResultList();

            ArrayList<ItemField> itemFields = new ArrayList<>();

            for (ItemFieldDAO dao : daoList) {
                int id = dao.getId();
                int fieldId = dao.getFieldId();
                String usernameOfOwner = dao.getUsernameOfOwner();
                String value = dao.getValue();

                assert itemId == dao.getItemId();
                ItemField itemField = fieldFactory.createItemField(id, itemId, fieldId, usernameOfOwner, value);
                itemFields.add(itemField);
            }

            entityManager.getTransaction().commit();

            return itemFields;

        } finally {
            entityManager.close();
        }
    }

    @Override
    public ItemField insertItemField(ItemField itemField) {

        ItemFieldDAO dao = this.createItemFieldDAO(itemField);

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            entityManager.persist(dao);
            entityManager.getTransaction().commit();

            int id = dao.getId();
            int itemId = dao.getItemId();
            int fieldId = dao.getFieldId();
            String usernameOfOwner = dao.getUsernameOfOwner();
            String value = dao.getValue();


            return fieldFactory.createItemField(id, itemId, fieldId, usernameOfOwner, value);

        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteItemField(ItemField itemField) {

        ItemFieldDAO dao = this.createItemFieldDAO(itemField);

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            entityManager.merge(dao);
            entityManager.remove(dao);
            entityManager.getTransaction().commit();

        } finally {
            entityManager.close();
        }
    }

    /**
     * Convenience method for {@link ItemRepositoryImpl} for retrieving {@link ItemField} objects.
     *
     * @param entityManager EntityManager with a {@link javax.transaction.Transaction} that has already begun.
     * @param itemId        The {@link Item} on which to filter.
     * @return Returns the set of {@link ItemField} objects associated to the {@link Item} with {@code itemId}.
     */
    List<ItemField> findItemFields(EntityManager entityManager, int itemId) {

        final String queryString = "SELECT o FROM ItemFieldDAO o WHERE o.itemId=:ITEM_ID";
        TypedQuery<ItemFieldDAO> query = entityManager.createQuery(queryString, ItemFieldDAO.class);
        query.setParameter("ITEM_ID", itemId);
        List<ItemFieldDAO> daoList = query.getResultList();

        ArrayList<ItemField> itemFields = new ArrayList<>();

        for (ItemFieldDAO dao : daoList) {
            int id = dao.getId();
            int fieldId = dao.getFieldId();
            String usernameOfOwner = dao.getUsernameOfOwner();
            String value = dao.getValue();

            assert itemId == dao.getItemId();
            ItemField itemField = fieldFactory.createItemField(id, itemId, fieldId, usernameOfOwner, value);
            itemFields.add(itemField);
        }

        entityManager.getTransaction().commit();

        return itemFields;

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

    /*
    A helper function to create the ItemField DAO.
 */
    private ItemFieldDAO createItemFieldDAO(ItemField itemField) {

        int id = itemField.getId();
        int itemId = itemField.getItemId();
        int fieldId = itemField.getFieldId();
        String username = itemField.getUsername();
        String value = itemField.getValue();

        ItemFieldDAO dao = new ItemFieldDAO();
        dao.setId(id);
        dao.setItemId(itemId);
        dao.setFieldId(fieldId);
        dao.setUsernameOfOwner(username);
        dao.setValue(value);

        return dao;
    }

}
