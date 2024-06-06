package me.ryzeon.domininghub.model;

import lombok.Getter;
import me.ryzeon.domininghub.shared.security.model.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 17:53
 */
@Document
public class User {

    @Id
    private String id;

    @Getter
    private String username;

    @Getter
    private String name;

    @Getter
    private String email;

    @Getter
    private String password;

    @Getter
    @DBRef
    private Set<Role> roles;

    public User() {
    }
}
