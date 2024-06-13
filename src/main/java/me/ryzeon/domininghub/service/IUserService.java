package me.ryzeon.domininghub.service;

import me.ryzeon.domininghub.dto.auth.SignUpRequest;
import me.ryzeon.domininghub.entity.User;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 17:53
 */
public interface IUserService {

    Optional<User> signUp(SignUpRequest request);

    Optional<User> findById(String id);

    Optional<ImmutablePair<User, String>> signIn(String usernameOrEmail, String password);
}
