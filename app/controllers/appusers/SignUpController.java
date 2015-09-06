package controllers.appusers;

import models.appusers.AppUser;

/**
 * Responsible for handling the Sign Up feature, creating a new {@link AppUser} in the database.
 */
public interface SignUpController {

    void signUpHandling(String username, String password);

}
