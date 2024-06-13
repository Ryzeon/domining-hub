package me.ryzeon.domininghub.shared.security.repository;

import me.ryzeon.domininghub.shared.security.entity.Role;
import me.ryzeon.domininghub.shared.security.entity.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 09/06/24 @ 23:45
 */
@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    boolean existsByName(Roles name);

    Optional<Role> findByName(Roles name);
}
