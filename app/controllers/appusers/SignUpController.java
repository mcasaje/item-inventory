package controllers.appusers;

import models.appusers.AppUser;

/**
 * Responsible for handling the Sign Up feature, creating a new {@link AppUser} in the database.
 */
public interface SignUpController {

    /**
     * Attempts to create a new user.
     *
     * @param username The desired username.
     * @param password The desired password used for authentication.
     * @throws UsernameTakenException Thrown when {@code username} already exists within the system.
     * @throws UsernameRequiredException Thrown when {@code username} is {@code null} or the empty string.
     * @throws PasswordRequiredException Thrown when {@code password} is {@code null} or the empty string.
     */
    void handleSignUp(String username, String password) throws UsernameTakenException, UsernameRequiredException, PasswordRequiredException;

}
