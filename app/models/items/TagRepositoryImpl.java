package models.items;

import models.jpa.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TagRepositoryImpl implements TagRepository {

    private JPAUtils jpaUtils;
    private TagFactory tagFactory;

    TagRepositoryImpl(JPAUtils jpaUtils, TagFactory tagFactory) {
        this.jpaUtils = jpaUtils;
        this.tagFactory = tagFactory;
    }

    @Override
    public Tag findTag(int id) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            final String queryString = "SELECT o FROM TagDAO o WHERE o.id=:ID";
            TypedQuery<TagDAO> query = entityManager.createQuery(queryString, TagDAO.class);
            query.setParameter("ID", id);
            TagDAO dao = query.getSingleResult();
            String name = dao.getName();
            String usernameOfOwner = dao.getUsernameOfOwner();

            assert id == dao.getId();

            entityManager.getTransaction().commit();

            return tagFactory.createTag(id, name, usernameOfOwner);

        } finally {
            entityManager.close();
        }
    }

    @Override
    public Tag findTag(String name, String usernameOfOwner) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            final String queryString = "SELECT o FROM TagDAO o WHERE o.name=:NAME AND o.usernameOfOwner=:USERNAME_OF_OWNER";
            TypedQuery<TagDAO> query = entityManager.createQuery(queryString, TagDAO.class);
            query.setParameter("NAME", name);
            query.setParameter("USERNAME_OF_OWNER", usernameOfOwner);
            TagDAO dao = query.getSingleResult();
            int id = dao.getId();

            assert name.equals(dao.getName());
            assert usernameOfOwner.equals(dao.getUsernameOfOwner());

            entityManager.getTransaction().commit();

            return tagFactory.createTag(id, name, usernameOfOwner);

        } finally {
            entityManager.close();
        }
    }

    @Override
    public Set<Tag> findTags(String usernameOfOwner) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            final String queryString = "SELECT o FROM TagDAO o WHERE o.usernameOfOwner=:USERNAME_OF_OWNER";
            TypedQuery<TagDAO> query = entityManager.createQuery(queryString, TagDAO.class);
            query.setParameter("USERNAME_OF_OWNER", usernameOfOwner);
            List<TagDAO> daoList = query.getResultList();

            HashSet<Tag> tags = new HashSet<>();

            for (TagDAO dao : daoList) {
                int id = dao.getId();
                String name = dao.getName();

                assert usernameOfOwner.equals(dao.getUsernameOfOwner());

                Tag tag = tagFactory.createTag(id, name, usernameOfOwner);

                tags.add(tag);
            }

            entityManager.getTransaction().commit();

            return tags;

        } finally {
            entityManager.close();
        }
    }

    @Override
    public Tag insertTag(Tag tag) {

        TagDAO dao = this.createDAO(tag);

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            entityManager.persist(dao);
            entityManager.getTransaction().commit();

            int id = dao.getId();
            String name = dao.getName();
            String usernameOfOwner = dao.getUsernameOfOwner();

            return tagFactory.createTag(id, name, usernameOfOwner);

        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteTag(Tag tag) {

        TagDAO dao = this.createDAO(tag);

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
     * Convenience method for {@link ItemRepositoryImpl} for retrieving {@link Tag} objects.
     *
     * @param entityManager EntityManager with a {@link javax.transaction.Transaction} that has already begun.
     * @param itemId        The {@link Item} on which to filter.
     * @return Returns the set of {@link Tag} objects associated to the {@link Item} with {@code itemId}.
     */
    Set<Tag> findTags(EntityManager entityManager, int itemId) {

        final String queryString = "SELECT t.* FROM tag AS t RIGHT JOIN (\n" +
                "  SELECT * FROM item_tag AS it WHERE it.item_id = :ITEM_ID\n" +
                ") AS assoc ON assoc.tag_id = t.id AND assoc.username = t.username;";
        Query query = entityManager.createNativeQuery(queryString, TagDAO.class);
        query.setParameter("ITEM_ID", itemId);

        @SuppressWarnings("unchecked")
        List<TagDAO> daoList = (List<TagDAO>) query.getResultList();

        HashSet<Tag> tags = new HashSet<>();

        for (TagDAO dao : daoList) {
            int id = dao.getId();
            String name = dao.getName();
            String usernameOfOwner = dao.getUsernameOfOwner();
            Tag tag = tagFactory.createTag(id, name, usernameOfOwner);
            tags.add(tag);
        }

        return tags;

    }

    /*
        A helper function to create the DAO.
     */
    private TagDAO createDAO(Tag tag) {

        int id = tag.getId();
        String name = tag.getName();
        String username = tag.getUsername();

        TagDAO dao = new TagDAO();
        dao.setId(id);
        dao.setName(name);
        dao.setUsernameOfOwner(username);

        return dao;
    }

}
