package models.appusers;

public interface AppUserFactory {

    /**
     * Create an (@link AppUser} instance for a new user.
     *
     * @param username     The desired username.
     * @param passwordSalt The generated password salt.
     * @param passwordHash The password hash after applying the salt and its encryption .
     * @return Returns an instance of {@link AppUser} which can be used to save into the database.
     */
    AppUser create(String username, String passwordSalt, String passwordHash);

    /**
     * Create an {@link AppUser} instance from an existing user.
     *
     * @param id           The database-generated surrogate ID.
     * @param username     The username used for authentication.
     * @param passwordSalt The generated password salt.
     * @param passwordHash The encrypted password.
     * @return Returns an instance of {@link AppUser} that represents a registered user.
     */
    AppUser create(int id, String username, String passwordSalt, String passwordHash);

    /**
     * Creates a copy of the given instance of {@link AppUser}.
     *
     * @param appUser The instance to copy.
     * @return Returns a new instance of {@link AppUser}.
     */
    AppUser create(AppUser appUser);

}
