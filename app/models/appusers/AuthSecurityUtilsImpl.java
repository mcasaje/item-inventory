package models.appusers;

import org.jasypt.util.password.StrongPasswordEncryptor;

import java.math.BigInteger;
import java.security.SecureRandom;

class AuthSecurityUtilsImpl implements AuthSecurityUtils {

    private SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generateSalt(int bitLength) {
        return new BigInteger(bitLength, secureRandom).toString(256);
    }

    @Override
    public String encryptPassword(String salt, String plainPassword) {
        final String saltedPassword = salt + plainPassword;
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor.encryptPassword(saltedPassword);
    }
}
