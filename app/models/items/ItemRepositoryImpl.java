package models.items;

import models.jpa.JPAUtils;

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

    ItemRepositoryImpl(JPAUtils jpaUtils, ItemFactory itemFactory, TagRepositoryImpl tagRepository) {
        this.jpaUtils = jpaUtils;
        this.itemFactory = itemFactory;
        this.tagRepository = tagRepository;
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
            List<Field> fields = this.queryForFields(entityManager, id);
            List<Rating> ratings = this.queryForRating(entityManager, id);

            entityManager.getTransaction().commit();

            return itemFactory.createItem(id, name, usernameOfOwner, tags, fields, ratings);

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
            List<Field> fields = this.queryForFields(entityManager, id);
            List<Rating> ratings = this.queryForRating(entityManager, id);

            entityManager.getTransaction().commit();

            return itemFactory.createItem(id, name, usernameOfOwner, tags, fields, ratings);

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
                List<Field> fields = this.queryForFields(entityManager, id);
                List<Rating> ratings = this.queryForRating(entityManager, id);
                Item item = itemFactory.createItem(id, name, usernameOfOwner, tags, fields, ratings);
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

    // Package-private Methods - These utilize an EntityManager with an already-started transaction //

    Set<Tag> queryForTags(EntityManager entityManager, int itemId) {
        return tagRepository.findTags(entityManager, itemId);
    }

    List<Field> queryForFields(EntityManager entityManager, int itemId) {
        // TODO: Delegate to FieldRepository
        return null;
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
