package models.items.tags;

import models.items.Item;

import javax.persistence.EntityManager;
import java.util.List;

public interface TagRepository {

    Tag findTag(EntityManager entityManager, int id);

    Tag findTag(EntityManager entityManager, String name, String usernameOfOwner);

    List<Tag> findTags(EntityManager entityManager, String usernameOfOwner);

    List<Tag> findTagsForItemType(EntityManager entityManager, int itemTypeId);

    List<Tag> findTagsForItem(EntityManager entityManager, int itemId);

    Tag insertTag(EntityManager entityManager, String name, int itemTypeId, String usernameOfOwner);

    void insertItemTag(EntityManager entityManager, int itemId, int tagId, String usernameOfOwner);

    void deleteTag(EntityManager entityManager, Tag tag);

    void detachAllTagsFromItem(EntityManager entityManager, Item item);

}
