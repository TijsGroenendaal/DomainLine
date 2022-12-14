package nl.tijsgroenendaal.domainline.impl.user.middleware;

import nl.tijsgroenendaal.domainline.DomainMiddleware;
import nl.tijsgroenendaal.domainline.Order;
import nl.tijsgroenendaal.domainline.RequestHandler;
import nl.tijsgroenendaal.domainline.impl.user.UserDomain;
import nl.tijsgroenendaal.domainline.impl.user.command.CreateUserCommand;
import nl.tijsgroenendaal.domainline.impl.user.command.DeleteUserCommand;

public class UserDomainCommandAuthorizer implements DomainMiddleware<UserDomain> {

    @Order(10)
    @RequestHandler
    private void deleteUser(DeleteUserCommand command) {
        System.out.println("Authorize DeleteUser");
    }

    @Order(10)
    @RequestHandler
    private void createUser(CreateUserCommand command) {
        System.out.println("Authorize CreateUser");
    }


}
