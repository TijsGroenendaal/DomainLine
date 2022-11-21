package nl.tijsgroenendaal.domainline.exceptions;

public class ConflictingRequestHandlersException extends RuntimeException {
    public ConflictingRequestHandlersException(String request) {
        super(String.format("Multiple Request Handlers implemented for Request `%s`", request));
    }
}
