package models.appusers;

/**
 * Provides methods for persisting, updating, and deleting {@link AppUser} objects.
 */
public interface AppUserRepository {

    /**
     * Finds an existing {@link AppUser}.
     *
     * @param id The unique user id accessed by {@link AppUser#getId()}.
     * @return Returns an {@link AppUser} if found. Returns {@code null} otherwise.
     */
    AppUser find(int id);

    /**
     * Inserts a new {@link AppUser} given that its username is unique. The insertion generates the user's id.
     *
     * @param appUser The object to persist.
     * @return Returns the AppUser, updated with its new id.
     */
    AppUser insert(AppUser appUser);

    /**
     * Deletes an @{AppUser} from the database.
     *
     * @param appUser The object to delete.
     */
    void delete(AppUser appUser);

}
