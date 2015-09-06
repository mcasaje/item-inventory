package controllers.appusers;

import models.appusers.AppUser;
import models.appusers.AppUserFactory;
import models.appusers.AppUserRepository;
import models.appusers.AuthSecurityUtils;

import javax.inject.Inject;

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
    public void signUpHandling(final String username, final String password) {

        String salt = authSecurityUtils.generateSalt(bitLengthForSalt);
        String encryptPassword = authSecurityUtils.encryptPassword(salt, password);

        AppUser appUser = appUserFactory.create(username, salt, encryptPassword);
        appUser = appUserRepository.insert(appUser);

        System.out.println(appUser.getId());
        System.out.println(appUser.getUsername());
        System.out.println(appUser.getPasswordSalt());
        System.out.println(appUser.getPasswordHash());

    }
}
