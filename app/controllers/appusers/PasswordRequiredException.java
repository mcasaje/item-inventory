package controllers.appusers;

/**
 * Throw when a required password is not given as part of a request.
 */
public class PasswordRequiredException extends Exception {

    public PasswordRequiredException() {
    }

    public PasswordRequiredException(String message) {
        super(message);
    }

    public PasswordRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordRequiredException(Throwable cause) {
        super(cause);
    }

    public PasswordRequiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
