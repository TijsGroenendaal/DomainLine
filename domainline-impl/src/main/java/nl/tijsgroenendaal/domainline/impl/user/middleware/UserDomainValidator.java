package nl.tijsgroenendaal.domainline.impl.user.middleware;

import nl.tijsgroenendaal.domainline.DomainMiddleware;
import nl.tijsgroenendaal.domainline.Order;
import nl.tijsgroenendaal.domainline.RequestHandler;
import nl.tijsgroenendaal.domainline.impl.user.UserDomain;
import nl.tijsgroenendaal.domainline.impl.user.command.CreateUserCommand;
import nl.tijsgroenendaal.domainline.impl.user.command.DeleteUserCommand;
import nl.tijsgroenendaal.domainline.impl.user.query.GetUserQuery;

public class UserDomainValidator implements DomainMiddleware<UserDomain> {

    @RequestHandler
    private void deleteUser(DeleteUserCommand command) {
        System.out.println("Validate DeleteUser");
    }

    @Order(30)
    @RequestHandler
    private void createUser(CreateUserCommand command) {
        System.out.println("Validate CreateUser");
    }

    @Order(0)
    @RequestHandler
    private void getUser(GetUserQuery query) {
        System.out.println("Validate GetUser");
    }
}
