package nl.tijsgroenendaal.domainline.impl.user.middleware;

import nl.tijsgroenendaal.domainline.DomainMiddleware;
import nl.tijsgroenendaal.domainline.Order;
import nl.tijsgroenendaal.domainline.RequestHandler;
import nl.tijsgroenendaal.domainline.impl.user.UserDomain;
import nl.tijsgroenendaal.domainline.impl.user.query.GetUserQuery;

public class UserDomainQueryAuthorizer implements DomainMiddleware<UserDomain> {

    @Order(10)
    @RequestHandler
    private void getUser(GetUserQuery query) {
        System.out.println("Authorize GetUser");
    }

}
