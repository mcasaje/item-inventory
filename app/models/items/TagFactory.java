package models.items;

public interface TagFactory {

    Tag createTag(int id, String name, String username);

    Tag createTag(Tag tag);

}
