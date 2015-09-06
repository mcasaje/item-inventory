package models.items;

class TagFactoryImpl implements TagFactory {

    @Override
    public Tag createTag(int id, String name, String username) {
        return new TagImpl(id, name, username);
    }

    @Override
    public Tag createTag(Tag tag) {

        int id = tag.getId();
        String name = tag.getName();
        String username = tag.getUsername();

        return this.createTag(id, name, username);
    }
}
