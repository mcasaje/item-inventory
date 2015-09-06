package models.items.tags;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TagRepositoryImpl implements TagRepository {

    private TagFactory tagFactory;

    @Inject
    TagRepositoryImpl(TagFactory tagFactory) {
        this.tagFactory = tagFactory;
    }

    @Override
    public Tag findTag(EntityManager entityManager, int id) {

        final String queryString = "SELECT o FROM TagDAO o WHERE o.id=:ID";
        TypedQuery<TagDAO> query = entityManager.createQuery(queryString, TagDAO.class);
        query.setParameter("ID", id);
        TagDAO dao = query.getSingleResult();
        String name = dao.getName();
        String usernameOfOwner = dao.getUsernameOfOwner();

        assert id == dao.getId();


        return tagFactory.createTag(id, name, usernameOfOwner);
    }

    @Override
    public Tag findTag(EntityManager entityManager, String name, String usernameOfOwner) {

        final String queryString = "SELECT o FROM TagDAO o WHERE o.name=:NAME AND o.usernameOfOwner=:USERNAME_OF_OWNER";
        TypedQuery<TagDAO> query = entityManager.createQuery(queryString, TagDAO.class);
        query.setParameter("NAME", name);
        query.setParameter("USERNAME_OF_OWNER", usernameOfOwner);
        TagDAO dao = query.getSingleResult();
        int id = dao.getId();

        assert name.equals(dao.getName());
        assert usernameOfOwner.equals(dao.getUsernameOfOwner());

        return tagFactory.createTag(id, name, usernameOfOwner);
    }

    @Override
    public Set<Tag> findTags(EntityManager entityManager, String usernameOfOwner) {

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

        return tags;
    }

    @Override
    public Set<Tag> findTags(EntityManager entityManager, int itemId) {

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

    @Override
    public Tag insertTag(EntityManager entityManager, Tag tag) {

        TagDAO dao = this.createDAO(tag);

        entityManager.persist(dao);

        int id = dao.getId();
        String name = dao.getName();
        String usernameOfOwner = dao.getUsernameOfOwner();

        return tagFactory.createTag(id, name, usernameOfOwner);

    }

    @Override
    public void deleteTag(EntityManager entityManager, Tag tag) {

        TagDAO dao = this.createDAO(tag);

        entityManager.merge(dao);
        entityManager.remove(dao);
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
