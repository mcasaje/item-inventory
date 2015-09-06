package controllers.appusers;

import models.appusers.AppUser;
import models.appusers.AppUserFactory;
import models.appusers.AppUserRepository;
import models.appusers.AuthSecurityUtils;

import javax.inject.Inject;
import javax.persistence.RollbackException;

class SignUpControllerImpl implements SignUpController {

    private AuthSecurityUtils authSecurityUtils;
    private AppUserFactory appUserFactory;
    private AppUserRepository appUserRepository;

    private final int bitLengthForSalt = 100;

    @Inject
    SignUpControllerImpl(AuthSecurityUtils authSecurityUtils, AppUserFactory appUserFactory, AppUserRepository appUserRepository) {
        this.authSecurityUtils = authSecurityUtils;
        this.appUserFactory = appUserFactory;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void handleSignUp(final String dirtyUsername, final String dirtyPassword) throws UsernameTakenException, UsernameRequiredException, PasswordRequiredException {

        String username;
        String password;

        if (dirtyUsername != null && !dirtyUsername.equals("")) {
            // TODO: Do more sanitization
            username = dirtyUsername;
        } else {
            throw new UsernameRequiredException();
        }

        if (dirtyPassword != null && !dirtyPassword.equals("")) {
            // TODO: Do more sanitization
            password = dirtyPassword;
        } else {
            throw new PasswordRequiredException();
        }

        try {

            String salt = authSecurityUtils.generateSalt(bitLengthForSalt);
            String encryptPassword = authSecurityUtils.encryptPassword(salt, password);

            AppUser appUser = appUserFactory.create(username, salt, encryptPassword);
            appUserRepository.insert(appUser);

        } catch (RollbackException e) {
            throw new UsernameTakenException();
        }
    }
}
