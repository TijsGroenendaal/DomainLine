package nl.tijsgroenendaal.domainline.impl;

import nl.tijsgroenendaal.domainline.DomainHandler;
import nl.tijsgroenendaal.domainline.DomainMiddleware;
import nl.tijsgroenendaal.domainline.Pipeline;
import nl.tijsgroenendaal.domainline.impl.order.ProductDomainCommandHandler;
import nl.tijsgroenendaal.domainline.impl.order.ProductDomainCommandValidator;
import nl.tijsgroenendaal.domainline.impl.order.commands.CreateProductCommand;
import nl.tijsgroenendaal.domainline.impl.user.UserDomainCommandHandler;
import nl.tijsgroenendaal.domainline.impl.user.UserDomainQueryHandler;
import nl.tijsgroenendaal.domainline.impl.user.command.CreateUserCommand;
import nl.tijsgroenendaal.domainline.impl.user.command.DeleteUserCommand;
import nl.tijsgroenendaal.domainline.impl.user.middleware.UserDomainCommandAuthorizer;
import nl.tijsgroenendaal.domainline.impl.user.middleware.UserDomainQueryAuthorizer;
import nl.tijsgroenendaal.domainline.impl.user.middleware.UserDomainValidator;
import nl.tijsgroenendaal.domainline.impl.user.query.GetUserQuery;

public class Main {

    public static void main(String[] args) {
        var pipeline = new Pipeline()
                .with(new DomainHandler[]{
                        new UserDomainCommandHandler(),
                        new UserDomainQueryHandler(),
                        new ProductDomainCommandHandler()
                })
                .with(new DomainMiddleware[]{
                        new UserDomainQueryAuthorizer(),
                        new UserDomainCommandAuthorizer(),
                        new UserDomainValidator(),
                        new ProductDomainCommandValidator()
                });

        var createProductCommand = new CreateProductCommand("Laptop", "Laptop");
        var createProductCommandResult = send(() -> pipeline.send(createProductCommand));

        var createUserCommand = new CreateUserCommand("tijs.groenendaal", "abcdef");
        var createUserCommandResult = send(() -> pipeline.send(createUserCommand));

        var getUserQuery = new GetUserQuery(createUserCommandResult.id());
        var getUserQueryResult = send(() -> pipeline.send(getUserQuery));

        var deleteUserCommand = new DeleteUserCommand(createUserCommandResult.id());
        var deleteUserCommandResult = send(() -> pipeline.send(deleteUserCommand));

        var deleteUserCommandIgnored = new DeleteUserCommand(createUserCommandResult.id());
        deleteUserCommandIgnored.registerIgnoredMiddleware(new Class[]{
                UserDomainValidator.class,
                UserDomainCommandAuthorizer.class
        });
        var deleteUserCommandIgnoredResult = send(() -> pipeline.send(deleteUserCommandIgnored));
    }

    private static <R> R send(CallBack<R> callBack) {
        var start = System.nanoTime();
        var result = callBack.call();
        var stop = System.nanoTime();
        System.out.printf("EXECUTION TIME: %f \n\n", (stop - start) / 1000_000.0);
        return result;
    }

    private interface CallBack<R>  {
        R call();
    }

}