package net.wherewhat.matereal.startup;

/**
 * @author RyuIkHan
 */
public class StartUpException extends RuntimeException{

    public StartUpException() {
        super();
    }

    public StartUpException(String detailMessage) {
        super(detailMessage);
    }

    public StartUpException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public StartUpException(Throwable throwable) {
        super(throwable);
    }
}
