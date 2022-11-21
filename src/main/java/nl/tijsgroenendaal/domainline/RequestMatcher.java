package nl.tijsgroenendaal.domainline;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;

public interface RequestMatcher {
    default LinkedList<Method> getMatchingHandler(Request<?, ?> request) {
        var methods = new LinkedList<Method>();
        for (var method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(RequestHandler.class) &&
                method.getParameterCount() == 1 &&
                method.getParameterTypes()[0].isAssignableFrom(request.getClass()) &&
                (
                        ((ParameterizedType) this.getClass().getGenericInterfaces()[0]).getRawType() == DomainMiddleware.class ||
                        method.getReturnType() == ((ParameterizedType) request.getClass().getGenericSuperclass()).getActualTypeArguments()[0]
                )
            ) {
                methods.add(method);
            }
        }
        return methods;
    }
}
