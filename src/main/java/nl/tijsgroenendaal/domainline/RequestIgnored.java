package nl.tijsgroenendaal.domainline;

import java.util.ArrayList;
import java.util.List;

public class RequestIgnored<D extends Domain> {

    private final ArrayList<Class<DomainMiddleware<D>>> ignoredMiddleware = new ArrayList<>();

    public void registerIgnoredMiddleware(Class<DomainMiddleware<D>>[] ignored) {
        this.ignoredMiddleware.addAll(List.of(ignored));
    }

    public final ArrayList<Class<DomainMiddleware<D>>> getIgnoredMiddleware() {
        return this.ignoredMiddleware;
    }
}
