package com.example.ravin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN(Set.of("ROLE_ADMIN", "ROLE_USER")),
    USER(Set.of("ROLE_USER"));

    private final Set<String> roles;
}
