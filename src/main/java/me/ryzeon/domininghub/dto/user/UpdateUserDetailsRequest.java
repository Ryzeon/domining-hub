package me.ryzeon.domininghub.dto.user;

public record UpdateUserDetailsRequest(
        String names,
        String username,
        String lastNames,
        String position,
        String company,
        String about
) {
}
