package models.appusers;

/**
 * Provides methods for encryption, random string generation for salts, and password matching.
 */
public interface AuthSecurityUtils {

    String generateSalt(int bitLength);

    String encryptPassword(String salt, String plainPassword);

}
