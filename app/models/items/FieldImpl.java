package models.items;

class FieldImpl implements Field {

    private int id;
    private String name;
    private String usernameOfOwner;

    FieldImpl(int id, String name, String usernameOfOwner) {
        this.id = id;
        this.name = name;
        this.usernameOfOwner = usernameOfOwner;
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
        return usernameOfOwner;
    }

    @Override
    public int compareTo(Field o) {
        String otherName = o.getName();
        return this.name.compareTo(otherName);
    }
}
