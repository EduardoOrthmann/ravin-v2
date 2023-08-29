package com.example.ravin.domains.auth;

import com.example.ravin.domains.user.User;
import com.example.ravin.domains.user.UserService;
import com.example.ravin.domains.dtos.request.LoginRequestDto;
import com.example.ravin.exceptions.UserAlreadyExistsException;
import com.example.ravin.utils.JwtUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private static final String USER_NOT_FOUND_MESSAGE = "Usuário não encontrado";

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginRequestDto loginRequestDto) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getLogin(), loginRequestDto.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            throw new EntityNotFoundException(USER_NOT_FOUND_MESSAGE);
        }

        return generateToken(authentication);
    }

    public User register(User user) {
        if (userService.existsByLogin(user.getLogin())) {
            throw new UserAlreadyExistsException();
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        return userService.save(new User(user.getLogin(), encodedPassword, user.getRole()));
    }

    private String generateToken(Authentication authentication) {
        return jwtUtils.generateToken((User) authentication.getPrincipal());
    }
}
