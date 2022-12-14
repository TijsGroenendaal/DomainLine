package nl.tijsgroenendaal.domainline.impl.user.model;

import java.util.UUID;

public record UserModel(
        UUID id,
        String username,
        String password
) {

}
