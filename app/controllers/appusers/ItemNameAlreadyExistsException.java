package controllers.appusers;

public class ItemNameAlreadyExistsException extends Exception {
    public ItemNameAlreadyExistsException() {
    }

    public ItemNameAlreadyExistsException(String message) {
        super(message);
    }

    public ItemNameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNameAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ItemNameAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
