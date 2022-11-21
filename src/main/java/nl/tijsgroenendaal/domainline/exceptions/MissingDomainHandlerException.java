package nl.tijsgroenendaal.domainline.exceptions;

public class MissingDomainHandlerException extends RuntimeException {
    public MissingDomainHandlerException(String request) {
        super(String.format("No Handler implemented for Request '%s'", request));
    }
}
