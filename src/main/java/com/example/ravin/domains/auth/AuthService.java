package com.example.ravin.domains.auth;

import com.example.ravin.domains.user.User;
import com.example.ravin.domains.user.UserService;
import com.example.ravin.dtos.AuthRegisterRequestDto;
import com.example.ravin.dtos.AuthRequestDto;
import com.example.ravin.exceptions.UserAlreadyExistsException;
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
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public String login(AuthRequestDto authRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        return generateToken(authentication);
    }

    public User register(AuthRegisterRequestDto authRegisterRequest) {
        if (userService.existsByLogin(authRegisterRequest.getLogin())) {
            throw new UserAlreadyExistsException();
        }

        String encodedPassword = passwordEncoder.encode(authRegisterRequest.getPassword());

        return userService.save(new User(authRegisterRequest.getLogin(), encodedPassword, authRegisterRequest.getRole()));
    }

    private String generateToken(Authentication authentication) {
        return tokenService.generateToken((User) authentication.getPrincipal());
    }
}
