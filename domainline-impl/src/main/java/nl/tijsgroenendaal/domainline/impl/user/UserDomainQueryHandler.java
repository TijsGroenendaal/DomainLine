package nl.tijsgroenendaal.domainline.impl.user;

import nl.tijsgroenendaal.domainline.DomainHandler;
import nl.tijsgroenendaal.domainline.RequestHandler;
import nl.tijsgroenendaal.domainline.impl.user.model.UserModel;
import nl.tijsgroenendaal.domainline.impl.user.query.GetUserQuery;

public class UserDomainQueryHandler implements DomainHandler<UserDomain> {

    @RequestHandler
    private UserModel getUser(GetUserQuery query) {
        System.out.println("Get User: " + query.id);
        return new UserModel(query.id, "username", "password");
    }

}
