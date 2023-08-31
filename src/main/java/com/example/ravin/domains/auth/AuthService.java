package com.example.ravin.domains.auth;

import com.example.ravin.domains.dtos.request.UserRequestDto;
import com.example.ravin.domains.dtos.response.UserResponseDto;
import com.example.ravin.domains.user.User;
import com.example.ravin.domains.user.UserService;
import com.example.ravin.domains.dtos.request.LoginRequestDto;
import com.example.ravin.utils.JwtUtils;
import com.example.ravin.utils.constants.ErrorMessages;
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
    private final PasswordEncoder passwordEncoder;

    public String login(LoginRequestDto loginRequestDto) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getLogin(), loginRequestDto.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            throw new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }

        return JwtUtils.generateToken((User) authentication.getPrincipal());
    }

    public UserResponseDto register(UserRequestDto userRequestDto) {
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());

        return userService.save(new UserRequestDto(userRequestDto.getLogin(), encodedPassword, userRequestDto.getRole()));
    }
}
