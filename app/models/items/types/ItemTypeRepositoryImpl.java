package models.items.types;

import models.items.fields.Field;
import models.items.fields.FieldRepository;
import models.items.tags.Tag;
import models.items.tags.TagRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class ItemTypeRepositoryImpl implements ItemTypeRepository {

    private ItemTypeFactory itemTypeFactory;
    private TagRepository tagRepository;
    private FieldRepository fieldRepository;

    @Inject
    ItemTypeRepositoryImpl(ItemTypeFactory itemTypeFactory, TagRepository tagRepository, FieldRepository fieldRepository) {
        this.itemTypeFactory = itemTypeFactory;
        this.tagRepository = tagRepository;
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
        String usernameOfOwner = itemTypeDAO.getUsernameOfOwner();

        List<Field> fields = fieldRepository.findFields(entityManager, id);
        Set<Tag> tags = tagRepository.findTags(entityManager, id);

        return itemTypeFactory.createItemType(id, name, usernameOfOwner, tags, fields);
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
            assert username.equals(dao.getUsernameOfOwner());

            List<Field> fields = fieldRepository.findFields(entityManager, id);
            Set<Tag> tags = tagRepository.findTags(entityManager, id);

            ItemType itemType = itemTypeFactory.createItemType(id, name, username, tags, fields);
            itemTypes.add(itemType);

        }

        itemTypes.trimToSize();

        return itemTypes;

    }

    @Override
    public ItemType insert(EntityManager entityManager, String name, String username) {

        ItemTypeDAO dao = new ItemTypeDAO();
        dao.setName(name);
        dao.setUsernameOfOwner(username);

        entityManager.persist(dao);

        int id = dao.getId();

        return itemTypeFactory.createItemType(id, name, username, null, null);
    }

    @Override
    public void delete(EntityManager entityManager, ItemType itemType) {

        ItemTypeDAO dao = this.createDAO(itemType);

        entityManager.merge(dao);
        entityManager.remove(dao);

    }

    private ItemTypeDAO createDAO(ItemType itemType) {

        int id = itemType.getId();
        String name = itemType.getName();
        String username = itemType.getUsername();

        ItemTypeDAO dao = new ItemTypeDAO();
        dao.setId(id);
        dao.setName(name);
        dao.setUsernameOfOwner(username);

        return dao;
    }

}
