package models.items.types;

import models.items.fields.Field;
import models.items.fields.FieldRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

class ItemTypeRepositoryImpl implements ItemTypeRepository {

    private ItemTypeFactory itemTypeFactory;
    private FieldRepository fieldRepository;

    @Inject
    ItemTypeRepositoryImpl(ItemTypeFactory itemTypeFactory, FieldRepository fieldRepository) {
        this.itemTypeFactory = itemTypeFactory;
        this.fieldRepository = fieldRepository;
    }

    @Override
    public ItemType findItemType(EntityManager entityManager, int id) {

        final String queryString = "SELECT o FROM ItemTypeDAO o WHERE o.id=:ID";
        TypedQuery<ItemTypeDAO> query = entityManager.createQuery(queryString, ItemTypeDAO.class);
        query.setParameter("ID", id);
        ItemTypeDAO itemTypeDAO = query.getSingleResult();

        assert id == itemTypeDAO.getId();

        String name = itemTypeDAO.getName();

        List<Field> fields = fieldRepository.findFields(entityManager, id);

        return itemTypeFactory.createItemType(id, name, fields);
    }

    @Override
    public List<ItemType> findItemTypes(EntityManager entityManager, String username) {

        final String queryString = "SELECT o FROM ItemTypeDAO o WHERE o.usernameOfOwner=:USERNAME_OF_OWNER";
        TypedQuery<ItemTypeDAO> query = entityManager.createQuery(queryString, ItemTypeDAO.class);
        query.setParameter("USERNAME_OF_OWNER", username);
        List<ItemTypeDAO> itemTypeDAOList = query.getResultList();

        ArrayList<ItemType> itemTypes = new ArrayList<>();

        for (ItemTypeDAO dao : itemTypeDAOList) {

            assert username.equals(dao.getUsernameOfOwner());

            int id = dao.getId();
            String name = dao.getName();

            List<Field> fields = fieldRepository.findFields(entityManager, id);

            ItemType itemType = itemTypeFactory.createItemType(id, name, fields);
            itemTypes.add(itemType);

        }

        itemTypes.trimToSize();

        return itemTypes;

    }
}
