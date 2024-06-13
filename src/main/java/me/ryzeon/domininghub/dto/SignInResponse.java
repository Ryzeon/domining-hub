package me.ryzeon.domininghub.dto;

import me.ryzeon.domininghub.entity.User;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 12/06/24 @ 18:26
 */
public record SignInResponse(
        String id,
        String username,
        String email,
        String token
) {

    public static SignInResponse fromDetails(ImmutablePair<User, String> details) {
        return new SignInResponse(
                details.getLeft().getId(),
                details.getLeft().getUsername(),
                details.getLeft().getEmail(),
                details.getRight()
        );
    }
}
