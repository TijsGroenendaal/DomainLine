package nl.tijsgroenendaal.domainline;

public interface DomainMiddleware<D extends Domain> extends DomainMatcher, RequestMatcher {

}
