package models.items.tags;

import models.items.Item;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

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
    public List<Tag> findTags(EntityManager entityManager, String usernameOfOwner) {

        final String queryString = "SELECT o FROM TagDAO o WHERE o.usernameOfOwner=:USERNAME_OF_OWNER";
        TypedQuery<TagDAO> query = entityManager.createQuery(queryString, TagDAO.class);
        query.setParameter("USERNAME_OF_OWNER", usernameOfOwner);
        List<TagDAO> daoList = query.getResultList();

        ArrayList<Tag> tags = new ArrayList<>();

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
    public List<Tag> findTags(EntityManager entityManager, int itemTypeId) {

        final String queryString = "SELECT o FROM TagDAO o WHERE o.itemTypeId=:ITEM_TYPE_ID ORDER BY o.name ASC";
        TypedQuery<TagDAO> query = entityManager.createQuery(queryString, TagDAO.class);
        query.setParameter("ITEM_TYPE_ID", itemTypeId);
        List<TagDAO> daoList = query.getResultList();

        ArrayList<Tag> tags = new ArrayList<>();

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
    public void insertItemTag(EntityManager entityManager, int itemId, int tagId, String username) {

        ItemTagDAO dao = new ItemTagDAO();
        dao.setItemId(itemId);
        dao.setTagId(tagId);
        dao.setUsernameOfOwner(username);

        entityManager.persist(dao);

    }

    @Override
    public void deleteTag(EntityManager entityManager, Tag tag) {

        TagDAO dao = this.createDAO(tag);

        entityManager.merge(dao);
        entityManager.remove(dao);
    }

    @Override
    public void detachAllTagsFromItem(EntityManager entityManager, Item item) {

        final String queryString = "DELETE FROM ItemTagDAO o WHERE o.itemId=:ITEM_ID";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("ITEM_ID", item.getId());
        query.executeUpdate();

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
