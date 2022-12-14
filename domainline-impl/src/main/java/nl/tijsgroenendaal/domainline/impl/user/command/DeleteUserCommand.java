package nl.tijsgroenendaal.domainline.impl.user.command;

import nl.tijsgroenendaal.domainline.Request;
import nl.tijsgroenendaal.domainline.Unit;
import nl.tijsgroenendaal.domainline.impl.user.UserDomain;

import java.util.UUID;

public class DeleteUserCommand extends Request<Unit, UserDomain> {
    public UUID id;

    public DeleteUserCommand(UUID id) {
        this.id = id;
    }
}
