package controllers.appusers;

import models.appusers.AppUser;
import models.appusers.AppUserRepository;
import models.appusers.AuthSecurityUtils;

import javax.inject.Inject;
import javax.persistence.NoResultException;

class LoginControllerImpl implements LoginController {

    private AuthSecurityUtils authSecurityUtils;
    private AppUserRepository appUserRepository;

    @Inject
    LoginControllerImpl(AuthSecurityUtils authSecurityUtils, AppUserRepository appUserRepository) {
        this.authSecurityUtils = authSecurityUtils;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser handleAuthentication(String dirtyUsername, String dirtyPassword) throws UsernameRequiredException, PasswordRequiredException, UserDoesNotExistException, InvalidPasswordException {

        String username;
        String password;

        if (dirtyUsername != null && !dirtyUsername.equals("")) {
            username = dirtyUsername;
        } else {
            throw new UsernameRequiredException();
        }

        if (dirtyPassword != null && !dirtyPassword.equals("")) {
            password = dirtyPassword;
        } else {
            throw new PasswordRequiredException();
        }

        try {

            AppUser appUser = appUserRepository.find(username);

            String passwordHash = appUser.getPasswordHash();
            String passwordSalt = appUser.getPasswordSalt();

            boolean passwordMatches = authSecurityUtils.comparePassword(passwordSalt, password, passwordHash);

            if (passwordMatches) {
                return appUser;
            } else {
                throw new InvalidPasswordException();
            }


        } catch (NoResultException e) {
            throw new UserDoesNotExistException();
        }

    }
}
