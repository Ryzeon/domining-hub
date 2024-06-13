package me.ryzeon.domininghub.entity;

import lombok.Getter;
import me.ryzeon.domininghub.shared.security.entity.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private String lastName;

    private String email;

    private String password;

    @DBRef
    private Set<Role> roles;

    @Unwrapped(onEmpty = Unwrapped.OnEmpty.USE_NULL, prefix = "info_")
    private UserInfo info;

    public User() {
    }

    public User(String names, String lastName, String email, String password, Set<Role> roles, UserInfo info) {
        this.names = names;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.info = info;
        this.username = getDefaultUsername();
    }

    public User(String names, String lastName, String email, String password, List<Role> roles) {
        this(names, lastName, email, password, new HashSet<>(roles), new UserInfo());
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
        String[] lastNames = this.lastName.split(" ");
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
}
