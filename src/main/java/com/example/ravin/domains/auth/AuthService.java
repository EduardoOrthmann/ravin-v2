package com.example.ravin.domains.auth;

import com.example.ravin.domains.user.User;
import com.example.ravin.domains.user.UserService;
import com.example.ravin.dtos.AuthRegisterRequestDto;
import com.example.ravin.dtos.AuthRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;

    public Optional<String> login(AuthRequestDto authRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            return Optional.empty();
        }

        return Optional.of(tokenService.generateToken((User) authentication.getPrincipal()));
    }

    public User register(AuthRegisterRequestDto authRegisterRequest) {
        if (userService.existsByLogin(authRegisterRequest.getLogin())) return null;

        String encodedPassword = new BCryptPasswordEncoder().encode(authRegisterRequest.getPassword());

        return userService.save(new User(authRegisterRequest.getLogin(), encodedPassword, authRegisterRequest.getRole()));
    }
}
