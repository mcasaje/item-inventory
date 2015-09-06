package models.items.tags;

class TagImpl implements Tag {

    private final int id;
    private final String name;
    private String username;

    TagImpl(int id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
