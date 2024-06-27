package me.ryzeon.domininghub.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import me.ryzeon.domininghub.dto.user.UpdateUserDetailsRequest;
import me.ryzeon.domininghub.shared.security.entity.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 17:53
 */
@Getter
@Document
public class User {

    @Id
    private String id;

    private String username;

    private String names;

    private String lastNames;

    private String email;

    private String password;

    @DBRef
    private Set<Role> roles;

    @Unwrapped(onEmpty = Unwrapped.OnEmpty.USE_NULL, prefix = "info_")
    private UserInfo info;

    public User() {
    }


    public void updateUserInfo(UpdateUserDetailsRequest request) {
        if (request.names() != null && !request.names().isBlank()) {
            this.names = request.names();
        }
        if (request.lastNames() != null && !request.lastNames().isBlank()) {
            this.lastNames = request.lastNames();
        }
        if (request.username() != null && !request.username().isBlank()) {
            this.username = request.username();
        }
        this.info = new UserInfo(
                request.position(),
                request.company(),
                request.about()
        );
    }

    public User(String names, String lastNames, String email, String password, Set<Role> roles, UserInfo info) {
        this.names = names;
        this.lastNames = lastNames;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.info = info;
        this.username = getDefaultUsername();
    }

    public User(String names, String lastNames, String email, String password, List<Role> roles) {
        this(names, lastNames, email, password, new HashSet<>(roles), new UserInfo());
    }

    public String getDefaultUsername() {
        String[] names = this.names.split(" ");
        StringBuilder username = new StringBuilder();
        for (String name : names) {
            if (name.length() > 2) {
                username.append(name, 0, 2);
            } else {
                username.append(name);
            }
        }
        username.append(".");
        String[] lastNames = this.lastNames.split(" ");
        for (String lastName : lastNames) {
            if (lastName.length() > 2) {
                username.append(lastName, 0, 2);
            } else {
                username.append(lastName);
            }
        }
        username.append(".");

        String random = String.valueOf((int) (Math.random() * 1000));
        username.append(random);
        return username.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id.equals(user.id);
    }

    public String getFullName() {
        return names + " " + lastNames;
    }
}
