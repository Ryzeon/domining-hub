package me.ryzeon.domininghub.dto.user;

import me.ryzeon.domininghub.entity.User;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 13/06/24 @ 00:27
 */
public record UserDto (
        String id,
        String username,
        String email,
        String names,
        String lastNames,
        String position,
        String company,
        String about
) {
    public static UserDto fromUser(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getNames(),
                user.getLastNames(),
                user.getInfo().getPosition(),
                user.getInfo().getCompany(),
                user.getInfo().getAbout()
        );
    }
}
