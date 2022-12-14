package nl.tijsgroenendaal.domainline.impl.user.command;

import nl.tijsgroenendaal.domainline.Request;
import nl.tijsgroenendaal.domainline.impl.user.UserDomain;
import nl.tijsgroenendaal.domainline.impl.user.model.UserModel;

public class CreateUserCommand extends Request<UserModel, UserDomain> {
    public String username;
    public String password;

    public CreateUserCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
