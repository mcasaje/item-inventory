package models.appusers;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;

/**
 * Provides methods for persisting, updating, and deleting {@link AppUser} objects.
 */
public interface AppUserRepository {

    /**
     * Finds an existing {@link AppUser}.
     *
     * @param id The unique user id accessed by {@link AppUser#getId()}.
     * @return Returns an {@link AppUser} if found.
     * @throws NoResultException Thrown when a user is not found.
     */
    AppUser find(int id) throws NoResultException;

    /**
     * Finds an existing {@link AppUser}.
     *
     * @param username The unique username accessed by {@link AppUser#getId()}.
     * @return Returns an {@link AppUser} if found.
     * @throws NoResultException Thrown when a user is not found.
     */
    AppUser find(String username) throws NoResultException;

    /**
     * Inserts a new {@link AppUser} given that its username is unique. The insertion generates the user's id.
     *
     * @param appUser The object to persist.
     * @return Returns the AppUser, updated with its new id.
     * @throws RollbackException Thrown when a primary or unique key already exists in the database.
     */
    AppUser insert(AppUser appUser) throws RollbackException;

    /**
     * Deletes an @{AppUser} from the database.
     *
     * @param appUser The object to delete.
     */
    void delete(AppUser appUser);

}
