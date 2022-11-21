package nl.tijsgroenendaal.domainline;

public interface DomainHandler<D extends Domain> extends DomainMatcher, RequestMatcher { }
