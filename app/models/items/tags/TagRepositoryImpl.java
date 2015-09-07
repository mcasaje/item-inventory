package models.items.tags;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
    public Set<Tag> findTags(EntityManager entityManager, int itemTypeId) {

        final String queryString = "SELECT o FROM TagDAO o WHERE o.itemTypeId=:ITEM_TYPE_ID";
        TypedQuery<TagDAO> query = entityManager.createQuery(queryString, TagDAO.class);
        query.setParameter("ITEM_TYPE_ID", itemTypeId);
        List<TagDAO> daoList = query.getResultList();

        ArrayList<TagDAO> fields = new ArrayList<>();

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
    public Tag insertTag(EntityManager entityManager, String name, int itemTypeId, String username) {

        TagDAO dao = new TagDAO();
        dao.setName(name);
        dao.setItemTypeId(itemTypeId);
        dao.setUsernameOfOwner(username);

        entityManager.persist(dao);

        int id = dao.getId();

        return tagFactory.createTag(id, name, username);

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
