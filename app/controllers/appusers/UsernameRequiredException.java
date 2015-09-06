package controllers.appusers;

/**
 * Throw when a required username is not given as part of a request.
 */
public class UsernameRequiredException extends Exception {

    public UsernameRequiredException() {
    }

    public UsernameRequiredException(String message) {
        super(message);
    }

    public UsernameRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameRequiredException(Throwable cause) {
        super(cause);
    }

    public UsernameRequiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
