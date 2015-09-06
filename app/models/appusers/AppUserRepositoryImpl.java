package models.appusers;

import models.jpa.JPAUtils;
import play.db.jpa.JPA;

import javax.inject.Inject;
import javax.persistence.EntityManager;

class AppUserRepositoryImpl implements AppUserRepository {

    private JPAUtils jpaUtils;
    private AppUserFactory appUserFactory;

    @Inject
    AppUserRepositoryImpl(JPAUtils jpaUtils, AppUserFactory appUserFactory) {
        this.jpaUtils = jpaUtils;
        this.appUserFactory = appUserFactory;
    }

    @Override
    public AppUser find(int id) {

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            AppUserDAO appUserDAO = entityManager.find(AppUserDAO.class, id);
            entityManager.getTransaction().commit();

            return appUserFactory.create(appUserDAO);

        } finally {
            entityManager.close();
        }

    }

    @Override
    public AppUser insert(AppUser appUser) {

        AppUserDAO dao = this.createDAO(appUser);

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            entityManager.persist(dao);
            entityManager.getTransaction().commit();

            return appUserFactory.create(dao);

        } finally {
            entityManager.close();
        }

    }

    @Override
    public void delete(AppUser appUser) {

        AppUserDAO dao = this.createDAO(appUser);

        EntityManager entityManager = jpaUtils.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            entityManager.remove(dao);
            entityManager.getTransaction().commit();

        } finally {
            entityManager.close();
        }
    }

    /*
        A helper function to create the DAO.
     */
    private AppUserDAO createDAO(AppUser appUser) {

        Integer id = appUser.getId();
        String username = appUser.getUsername();
        String passwordSalt = appUser.getPasswordSalt();
        String passwordHash = appUser.getPasswordHash();

        AppUserDAO appUserDAO = new AppUserDAO();
        appUserDAO.setUsername(username);
        appUserDAO.setPasswordSalt(passwordSalt);
        appUserDAO.setPasswordHash(passwordHash);

        if (id != null) {
            appUserDAO.setId(id);
        }

        return appUserDAO;

    }

}
