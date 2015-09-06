package models.jpa;

import javax.persistence.EntityManager;

/**
 * Provides JPA helper methods, such as those related to interacting with {@link javax.persistence.EntityManager} and
 * {@link javax.persistence.EntityManagerFactory}
 */
public interface JPAUtils {

    /**
     * Provides a newly created instance of {@link EntityManager}.
     * <p>
     * Note: {@link EntityManager} instances created this way must be closed upon finishing.
     *
     * @return Returns a new {@link EntityManager}
     */
    EntityManager createEntityManager();

}
