package com.example.ravin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Position {
    HOST(UserRole.EMPLOYEE),
    COOK(UserRole.EMPLOYEE),
    CASHIER(UserRole.EMPLOYEE),
    MANAGER(UserRole.ADMIN),
    WAITER(UserRole.EMPLOYEE),
    CLEANER(UserRole.EMPLOYEE);

    private final UserRole role;
}
