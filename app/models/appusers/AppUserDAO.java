package models.appusers;

import javax.persistence.*;

/**
 * Mutable, The Data Access Object representation of {@link AppUser} for ORM use.
 */
@Entity
@Table(name = "app_user")
class AppUserDAO implements AppUser {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password_salt")
    private String passwordSalt;

    @Column(name = "password_hash")
    private String passwordHash;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    void setId(int id) {
        this.id = id;
    }

    void setUsername(String username) {
        this.username = username;
    }

    void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
