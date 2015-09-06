package controllers.appusers;

import models.appusers.AppUser;

/**
 * Responsible for handling the Sign Up feature, creating a new {@link AppUser} in the database.
 */
public interface SignUpController {

    /**
     * Attempts to create a new user.
     *
     * @param username
     * @param password
     * @return Returns {@code true} when a new user is successfully created. Returns {@code false} otherwise.
     */
    void handleSignUp(String username, String password) throws UsernameTakenException, UsernameRequiredException, PasswordRequiredException;

}
