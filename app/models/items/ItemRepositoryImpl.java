package models.items;

import models.jpa.JPAUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Queries for {@link Item} instances.
 * <p>
 * Acting facade for querying {@link Tag}, {@link Field}, and {@link Rating} instances.
 */
class ItemRepositoryImpl implements ItemRepository {

    private final JPAUtils jpaUtils;
    private final ItemFactory itemFactory;
    private TagRepositoryImpl tagRepository;
    private FieldRepositoryImpl fieldRepository;

    @Inject
    ItemRepositoryImpl(JPAUtils jpaUtils, ItemFactory itemFactory, TagFactory tagFactory, FieldFactory fieldFactory) {
        this.jpaUtils = jpaUtils;
        this.itemFactory = itemFactory;
        this.tagRepository = new TagRepositoryImpl(jpaUtils, tagFactory);
        this.fieldRepository = new FieldRepositoryImpl(jpaUtils, fieldFactory);
    }

    public Item findItem(int id) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            final String queryString = "SELECT o FROM ItemDAO o WHERE o.id=:ID";
            TypedQuery<ItemDAO> query = entityManager.createQuery(queryString, ItemDAO.class);
            query.setParameter("ID", id);
            ItemDAO dao = query.getSingleResult();
            id = dao.getId();
            String name = dao.getName();
            String usernameOfOwner = dao.getUsernameOfOwner();

            Set<Tag> tags = this.queryForTags(entityManager, id);
            List<ItemField> itemFields = this.queryForItemFields(entityManager, id);
            List<Rating> ratings = this.queryForRating(entityManager, id);

            entityManager.getTransaction().commit();

            return itemFactory.createItem(id, name, usernameOfOwner, tags, itemFields, ratings);

        } finally {
            entityManager.close();
        }

    }

    public Item findItem(String name, String usernameOfOwner) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            final String queryString = "SELECT o FROM ItemDAO o WHERE o.name=:NAME AND o.usernameOfOwner=:USERNAME_OF_OWNER";
            TypedQuery<ItemDAO> query = entityManager.createQuery(queryString, ItemDAO.class);
            query.setParameter("NAME", name);
            query.setParameter("USERNAME_OF_OWNER", usernameOfOwner);
            ItemDAO dao = query.getSingleResult();
            int id = dao.getId();
            name = dao.getName();

            assert usernameOfOwner.equals(dao.getUsernameOfOwner());

            Set<Tag> tags = this.queryForTags(entityManager, id);
            List<ItemField> itemFields = this.queryForItemFields(entityManager, id);
            List<Rating> ratings = this.queryForRating(entityManager, id);

            entityManager.getTransaction().commit();

            return itemFactory.createItem(id, name, usernameOfOwner, tags, itemFields, ratings);

        } finally {
            entityManager.close();
        }
    }

    public List<Item> findItems(String usernameOfOwner) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            final String queryString = "SELECT o FROM ItemDAO o WHERE o.usernameOfOwner=:USERNAME_OF_OWNER";
            TypedQuery<ItemDAO> query = entityManager.createQuery(queryString, ItemDAO.class);
            query.setParameter("USERNAME_OF_OWNER", usernameOfOwner);
            List<ItemDAO> daoList = query.getResultList();

            ArrayList<Item> items = new ArrayList<>();

            for (ItemDAO dao : daoList) {
                int id = dao.getId();
                String name = dao.getName();

                assert usernameOfOwner.equals(dao.getUsernameOfOwner());

                Set<Tag> tags = this.queryForTags(entityManager, id);
                List<ItemField> itemFields = this.queryForItemFields(entityManager, id);
                List<Rating> ratings = this.queryForRating(entityManager, id);
                Item item = itemFactory.createItem(id, name, usernameOfOwner, tags, itemFields, ratings);
                items.add(item);
            }

            items.trimToSize();

            entityManager.getTransaction().commit();

            return items;

        } finally {
            entityManager.close();
        }
    }

    @Override
    public Item insertItem(Item item) {

        ItemDAO dao = this.createDAO(item);

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            entityManager.persist(dao);
            entityManager.getTransaction().commit();

            int id = dao.getId();
            String name = dao.getName();
            String usernameOfOwner = dao.getUsernameOfOwner();

            return itemFactory.createItem(id, name, usernameOfOwner);

        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteItem(Item item) {

        ItemDAO dao = this.createDAO(item);

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
    public Tag findTag(int id) {
        return tagRepository.findTag(id);
    }

    @Override
    public Tag findTag(String name, String usernameOfOwner) {
        return tagRepository.findTag(name, usernameOfOwner);
    }

    @Override
    public Set<Tag> findTags(String usernameOfOwner) {
        return tagRepository.findTags(usernameOfOwner);
    }

    @Override
    public Tag insertTag(Tag tag) {
        return tagRepository.insertTag(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        tagRepository.deleteTag(tag);
    }

    @Override
    public Field findField(int id) {
        return fieldRepository.findField(id);
    }

    @Override
    public Field findField(String name, String usernameOfOwner) {
        return fieldRepository.findField(name, usernameOfOwner);
    }

    @Override
    public List<Field> findFields(String usernameOfOwner) {
        return fieldRepository.findFields(usernameOfOwner);
    }

    @Override
    public Field insertField(Field field) {
        return fieldRepository.insertField(field);
    }

    @Override
    public void deleteField(Field field) {
        fieldRepository.deleteField(field);
    }

    @Override
    public ItemField findItemField(int id) {
        return fieldRepository.findItemField(id);
    }

    @Override
    public List<ItemField> findItemFields(int itemId) {
        return fieldRepository.findItemFields(itemId);
    }

    @Override
    public ItemField insertItemField(ItemField field) {
        return fieldRepository.insertItemField(field);
    }

    @Override
    public void deleteItemField(ItemField field) {
        fieldRepository.deleteItemField(field);
    }

    // Package-private Methods - These utilize an EntityManager with an already-started transaction //

    Set<Tag> queryForTags(EntityManager entityManager, int itemId) {
        return tagRepository.findTags(entityManager, itemId);
    }

    List<ItemField> queryForItemFields(EntityManager entityManager, int itemId) {
        return fieldRepository.findItemFields(entityManager, itemId);
    }

    List<Rating> queryForRating(EntityManager entityManager, int itemId) {
        // TODO: Delegate to RatingRepository
        return null;
    }

    private ItemDAO createDAO(Item item) {

        int id = item.getId();
        String name = item.getName();
        String username = item.getUsername();

        ItemDAO dao = new ItemDAO();
        dao.setId(id);
        dao.setName(name);
        dao.setUsernameOfOwner(username);

        return dao;
    }
}
