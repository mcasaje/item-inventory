package models.items;

import java.util.Set;

public interface TagRepository {

    Tag findTag(int id);

    Tag findTag(String name, String usernameOfOwner);

    Set<Tag> findTags(String usernameOfOwner);

    Tag insertTag(Tag tag);

    void deleteTag(Tag tag);

}
