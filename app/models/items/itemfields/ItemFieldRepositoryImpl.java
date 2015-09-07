package models.items.itemfields;

import models.items.Item;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

class ItemFieldRepositoryImpl implements ItemFieldRepository {

    private ItemFieldFactory itemFieldFactory;

    @Inject
    ItemFieldRepositoryImpl(ItemFieldFactory itemFieldFactory) {
        this.itemFieldFactory = itemFieldFactory;
    }

    @Override
    public ItemField findItemField(EntityManager entityManager, int id) {

        final String queryString = "SELECT o FROM ItemFieldDAO o WHERE o.id=:ID";
        TypedQuery<ItemFieldDAO> query = entityManager.createQuery(queryString, ItemFieldDAO.class);
        query.setParameter("ID", id);
        ItemFieldDAO dao = query.getSingleResult();
        int itemId = dao.getItemId();
        int fieldId = dao.getFieldId();
        String usernameOfOwner = dao.getUsernameOfOwner();
        String value = dao.getValue();

        assert id == dao.getId();

        return itemFieldFactory.createItemField(id, itemId, fieldId, usernameOfOwner, value);
    }

    @Override
    public List<ItemField> findItemFields(EntityManager entityManager, int itemId) {

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
            ItemField itemField = itemFieldFactory.createItemField(id, itemId, fieldId, usernameOfOwner, value);
            itemFields.add(itemField);
        }

        return itemFields;

    }

    @Override
    public ItemField insertItemField(EntityManager entityManager, int itemId, int fieldId, String username, String fieldValue) {

        ItemFieldDAO dao = new ItemFieldDAO();
        dao.setItemId(itemId);
        dao.setFieldId(fieldId);
        dao.setUsernameOfOwner(username);
        dao.setValue(fieldValue);

        entityManager.persist(dao);

        int id = dao.getId();

        return itemFieldFactory.createItemField(id, itemId, fieldId, username, fieldValue);

    }

    @Override
    public void deleteItemField(EntityManager entityManager, Item item) {

        final String queryString = "DELETE FROM ItemFieldDAO o WHERE o.itemId=:ITEM_ID";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("ITEM_ID", item.getId());
        query.executeUpdate();

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
