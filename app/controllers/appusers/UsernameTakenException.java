package controllers.appusers;

/**
 * Throw this exception on user creation if the username already exists.
 */
public class UsernameTakenException extends Exception {

    public UsernameTakenException() {
    }

    public UsernameTakenException(String message) {
        super(message);
    }

    public UsernameTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameTakenException(Throwable cause) {
        super(cause);
    }

    public UsernameTakenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
