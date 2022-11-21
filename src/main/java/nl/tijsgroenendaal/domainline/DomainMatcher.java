package nl.tijsgroenendaal.domainline;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface DomainMatcher {
    default boolean isMatchingDomain(Request<?, ?> request) {
        var domainType = this.getClass().getGenericInterfaces()[0];
        var requestType = request.getClass();

        Type handlerDomain;
        if (((ParameterizedType) domainType).getRawType() == DomainHandler.class ||
                ((ParameterizedType) domainType).getRawType() == DomainMiddleware.class
        ) {
            handlerDomain = domainType;
        } else {
            return false;
        }

        var handlerDomainClass = ((ParameterizedType) handlerDomain).getActualTypeArguments()[0];

        for (var type : ((ParameterizedType) requestType.getGenericSuperclass()).getActualTypeArguments()) {
            if (type == handlerDomainClass) return true;
        }
        return false;
    }
}
