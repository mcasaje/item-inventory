package models.items.tags;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

public interface TagRepository {

    Tag findTag(EntityManager entityManager, int id);

    Tag findTag(EntityManager entityManager, String name, String usernameOfOwner);

    List<Tag> findTags(EntityManager entityManager, String usernameOfOwner);

    List<Tag> findTags(EntityManager entityManager, int itemTypeId);

    Tag insertTag(EntityManager entityManager, String name, int itemTypeId, String usernameOfOwner);

    void insertItemTag(EntityManager entityManager, int itemId, int tagId, String usernameOfOwner);

    void deleteTag(EntityManager entityManager, Tag tag);

}
