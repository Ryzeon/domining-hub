package me.ryzeon.domininghub.service.impl;

import lombok.AllArgsConstructor;
import me.ryzeon.domininghub.dto.auth.SignUpRequest;
import me.ryzeon.domininghub.entity.User;
import me.ryzeon.domininghub.repository.UserRepository;
import me.ryzeon.domininghub.service.IUserService;
import me.ryzeon.domininghub.shared.security.entity.Role;
import me.ryzeon.domininghub.shared.security.entity.Roles;
import me.ryzeon.domininghub.shared.security.repository.RoleRepository;
import me.ryzeon.domininghub.shared.security.service.IBearerTokenService;
import me.ryzeon.domininghub.shared.security.service.IHashingService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 12/06/24 @ 18:34
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final IHashingService hashingService;
    private final IBearerTokenService tokenService;

    @Override
    public Optional<User> signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }

        var roles = request.roles() == null || request.roles().isEmpty() ? new ArrayList<Role>() : new ArrayList<>(request.roles()
                .stream()
                .map(r -> roleRepository.findByName(Roles.valueOf(r.toUpperCase())).orElseThrow(() -> new RuntimeException("Role not found")))
                .toList());

        if (roles.isEmpty()) {
            roles.add(roleRepository.findByName(Role.getDefaultRole().getName()).orElseThrow(() -> new RuntimeException("Role not found")));
        }

        var user = new User(
                request.names(),
                request.lastNames(),
                request.email(),
                hashingService.encode(request.password()),
                roles
        );

        userRepository.save(user);
        return userRepository.findByUsername(user.getUsername());
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<ImmutablePair<User, String>> signIn(String usernameOrEmail, String password) {
        var user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!hashingService.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        var token = tokenService.generateToken(user.getUsername());
        return Optional.of(new ImmutablePair<>(user, token));
    }
}
