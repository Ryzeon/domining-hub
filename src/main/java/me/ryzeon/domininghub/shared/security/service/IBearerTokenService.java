package me.ryzeon.domininghub.shared.security.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 18:03
 */
public interface IBearerTokenService {
    String generateToken(String username);

    String getBearerTokenFrom(HttpServletRequest request);

    String generateToken(Authentication authentication);

    String getUsernameFromToken(String token);

    boolean validateToken(String token);
}
