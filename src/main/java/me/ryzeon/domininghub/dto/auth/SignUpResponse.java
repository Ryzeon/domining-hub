package me.ryzeon.domininghub.dto.auth;

import me.ryzeon.domininghub.entity.User;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 12/06/24 @ 18:28
 */
public record SignUpResponse(
        String id,
        String username,
        String email,
        List<String> roles
) {

    public static SignUpResponse fromUser(User user) {
        return new SignUpResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles().stream().map(role -> role.getName().name()).toList()
        );
    }
}
