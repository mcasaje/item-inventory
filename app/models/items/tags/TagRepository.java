package models.items.tags;

import javax.persistence.EntityManager;
import java.util.Set;

public interface TagRepository {

    Tag findTag(EntityManager entityManager, int id);

    Tag findTag(EntityManager entityManager, String name, String usernameOfOwner);

    Set<Tag> findTags(EntityManager entityManager, String usernameOfOwner);

    Set<Tag> findTags(EntityManager entityManager, int itemTypeId);

    Tag insertTag(EntityManager entityManager, String name, int itemTypeId, String username);

    void deleteTag(EntityManager entityManager, Tag tag);

}
