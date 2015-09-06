package models.appusers;

/**
 * Immutable, represents the main user of the application.
 */
public interface AppUser {

    Integer getId();

    String getUsername();

    String getPasswordSalt();

    String getPasswordHash();

}
