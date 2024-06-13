package me.ryzeon.domininghub.dto.auth;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 12/06/24 @ 18:58
 */
public record SignInRequest(
        String usernameOrEmail,
        String password
) {
}
