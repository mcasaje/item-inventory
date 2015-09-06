package models.appusers;

/**
 * Immutable
 */
class AppUserImpl implements AppUser {

    private final Integer id;
    private final String username;
    private final String passwordSalt;
    private final String passwordHash;

    AppUserImpl(Integer id, String username, String passwordSalt, String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordSalt = passwordSalt;
        this.passwordHash = passwordHash;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPasswordSalt() {
        return passwordSalt;
    }

    @Override
    public String getPasswordHash() {
        return passwordHash;
    }

}
