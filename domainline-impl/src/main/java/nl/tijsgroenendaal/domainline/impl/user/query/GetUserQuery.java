package nl.tijsgroenendaal.domainline.impl.user.query;

import nl.tijsgroenendaal.domainline.Request;
import nl.tijsgroenendaal.domainline.impl.user.UserDomain;
import nl.tijsgroenendaal.domainline.impl.user.model.UserModel;

import java.util.UUID;

public class GetUserQuery extends Request<UserModel, UserDomain> {
    public UUID id;

    public GetUserQuery(UUID id) {
        this.id = id;
    }
}
