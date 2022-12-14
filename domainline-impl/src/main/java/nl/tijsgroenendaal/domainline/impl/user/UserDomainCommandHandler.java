package nl.tijsgroenendaal.domainline.impl.user;

import nl.tijsgroenendaal.domainline.DomainHandler;
import nl.tijsgroenendaal.domainline.RequestHandler;
import nl.tijsgroenendaal.domainline.Unit;
import nl.tijsgroenendaal.domainline.impl.user.command.CreateUserCommand;
import nl.tijsgroenendaal.domainline.impl.user.command.DeleteUserCommand;
import nl.tijsgroenendaal.domainline.impl.user.model.UserModel;

import java.util.UUID;

public class UserDomainCommandHandler implements DomainHandler<UserDomain> {

    @RequestHandler
    private UserModel createUser(CreateUserCommand command) {
        System.out.println("Create User: " + command.username + " " + command.password);
        return new UserModel(UUID.randomUUID(), command.username, command.password);
    }

    @RequestHandler
    private Unit deleteUser(DeleteUserCommand command) {
        System.out.println("Deleted User: " + command.id);
        return Unit.value;
    }

}
