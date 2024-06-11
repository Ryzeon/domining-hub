package me.ryzeon.domininghub.service;

import me.ryzeon.domininghub.model.User;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 17:53
 */
public interface IUserService {

    Optional<User> createUser(User user);
}
