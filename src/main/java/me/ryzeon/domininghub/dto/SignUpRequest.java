package me.ryzeon.domininghub.dto;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 12/06/24 @ 18:15
 */
public record SignUpRequest(
        String names,
        String lastNames,
        String email,
        String password,
        List<String> roles
) {
}
