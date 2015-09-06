package models.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class JPAUtilsImpl implements JPAUtils {

    private EntityManagerFactory entityManagerFactory;

    // See persistence.xml
    private static final String PERSISTENCE_UNTI_NAME = "MySQLPersistenceUnit";

    JPAUtilsImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNTI_NAME);
    }

    @Override
    public EntityManager createEntityManager() {
        return this.entityManagerFactory.createEntityManager();
    }
}
