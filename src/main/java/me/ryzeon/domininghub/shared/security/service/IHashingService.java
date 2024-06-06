package me.ryzeon.domininghub.shared.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 17:56
 */
public interface IHashingService extends PasswordEncoder {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
