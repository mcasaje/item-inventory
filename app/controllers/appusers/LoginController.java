package controllers.appusers;

import models.appusers.AppUser;
import play.mvc.Http;

/**
 * Handles authentication of users.
 */
public interface LoginController {

    /**
     * Authenticates the user by checking if the user exists and matching the existing uesr's password with the
     * {@code password} parameter given.
     *
     * @param username The username used to identify the user within the system.
     * @param password The password to match against the system's records for the given {@code username}.
     * @return Returns the valid {@link AppUser} upon success.
     * @throws UsernameRequiredException Thrown when the username is not given.
     * @throws PasswordRequiredException Thrown when the password is not given.
     * @throws UserDoesNotExistException Thrown when the user is not found in the database.
     * @throws InvalidPasswordException  Thrown when the password does not match with the database user's password.
     */
    AppUser handleAuthentication(String username, String password) throws UsernameRequiredException, PasswordRequiredException, UserDoesNotExistException, InvalidPasswordException;

}
