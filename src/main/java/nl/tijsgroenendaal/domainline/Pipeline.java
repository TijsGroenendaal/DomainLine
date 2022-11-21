package nl.tijsgroenendaal.domainline;

import nl.tijsgroenendaal.domainline.exceptions.RequestHandlerInvocationException;
import nl.tijsgroenendaal.domainline.exceptions.ConflictingRequestHandlersException;
import nl.tijsgroenendaal.domainline.exceptions.MissingDomainHandlerException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Pipeline {
    private DomainHandler<?>[] domainHandlers = new DomainHandler[0];
    private DomainMiddleware<?>[] domainMiddlewares = new DomainMiddleware[0];

    public Pipeline() { }

    public Pipeline with(DomainHandler<?>[] domainHandlers) {
        if (domainHandlers == null) throw new IllegalArgumentException("DomainHandlers must not be null");
        this.domainHandlers = domainHandlers;
        return this;
    }

    public Pipeline with(DomainMiddleware<?>[] domainMiddlewares) {
        if (domainMiddlewares == null) throw new IllegalArgumentException("Middlewares must not be null");
        this.domainMiddlewares = domainMiddlewares;
        return this;
    }

    public <R, C extends Request<R, ?>> R send(C request) {
        var handler = getHandler(request);
        invokeMiddlewares(request);
        return invokeHandler(handler, request);
    }

    private void invokeMiddlewares(Request<?, ?> request) {
        var middlewares = new LinkedList<Tuple<Integer, Tuple<DomainMiddleware<?>, Method>>>();

        matchFilter:
        for (var handler : domainMiddlewares) {
            if (handler.isMatchingDomain(request)) {
                for (var filter : request.getIgnoredMiddleware()) {
                    if (filter.isAssignableFrom(handler.getClass())) {
                        continue matchFilter;
                    }
                }

                var middlewareMethods = handler.getMatchingHandler(request);
                if (middlewareMethods.isEmpty()) continue;

                for (var method : middlewareMethods) {
                    Order orderAnnotation = method.getAnnotation(Order.class);
                    int order = orderAnnotation != null ? orderAnnotation.value() : 2147483647;

                    middlewares.add(new Tuple<>(order, new Tuple<>(handler, method)));
                }
            }
        }

        middlewares.sort(Comparator.comparing(Tuple::key));

        for (var tuple : middlewares) {
            var method = tuple.value().value();
            var middleware = tuple.value().key();
            method.setAccessible(true);

            invoke(middleware, method , request);
        }
    }

    private Tuple<DomainHandler<?>, Method> getHandler(Request<?, ?> request) {
        var handlers = new LinkedList<Tuple<DomainHandler<?>, Method>>();

        for (var handler : domainHandlers) {
            if (handler.isMatchingDomain(request)) {
                var handlerMethods = handler.getMatchingHandler(request);

                if (handlerMethods.isEmpty()) continue;

                if (handlers.size() > 1) {
                    throw new ConflictingRequestHandlersException(request.getClass().getSimpleName());
                }

                handlers.add(new Tuple<>(handler, handlerMethods.get(0)));
            }
        }

        if (handlers.size() == 0) {
            throw new MissingDomainHandlerException(request.getClass().getSimpleName());
        }

        if (handlers.size() > 1) {
            throw new ConflictingRequestHandlersException(request.getClass().getSimpleName());
        }

        return handlers.get(0);
    }

    private <R, C extends Request<R, ?>> R invokeHandler(Tuple<DomainHandler<?>, Method> handler, C request) {
        return invoke(handler.key(), handler.value(), request);
    }

    @SuppressWarnings("unchecked")
    private <R, C extends Request<R, ?>> R invoke(Object handler, Method method, C request) {
        method.setAccessible(true);
        try {
            return (R) method.invoke(handler, request);
        } catch (IllegalAccessException e) {
            throw new RequestHandlerInvocationException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new RequestHandlerInvocationException(e.getMessage(), e.getCause());
        }
    }
}
