package com.example.ravin.domains.auth.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean existsByLogin(String login);
}
