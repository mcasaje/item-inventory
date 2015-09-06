package models.appusers;

class AppUserFactoryImpl implements AppUserFactory {

    @Override
    public AppUser create(String username, String passwordSalt, String passwordHash) {
        return new AppUserImpl(null, username, passwordSalt, passwordHash);
    }

    @Override
    public AppUser create(int id, String username, String passwordSalt, String passwordHash) {
        return new AppUserImpl(id, username, passwordSalt, passwordHash);
    }

    @Override
    public AppUser create(AppUser appUser) {

        Integer id = appUser.getId();
        String username = appUser.getUsername();
        String passwordSalt = appUser.getPasswordSalt();
        String passwordHash = appUser.getPasswordHash();

        return new AppUserImpl(id, username, passwordSalt, passwordHash);
    }
}
