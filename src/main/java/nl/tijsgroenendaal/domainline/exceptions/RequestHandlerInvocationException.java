package nl.tijsgroenendaal.domainline.exceptions;

public class RequestHandlerInvocationException extends RuntimeException {
    public RequestHandlerInvocationException(String message) {
        super(message);
    }

    public RequestHandlerInvocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
