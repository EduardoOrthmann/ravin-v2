package com.example.ravin.domains.auth;

import com.example.ravin.domains.dtos.request.UserRequestDto;
import com.example.ravin.domains.dtos.request.LoginRequestDto;
import com.example.ravin.domains.dtos.response.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final String HEADER_STRING = "Authorization";

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok().header(HEADER_STRING, authService.login(loginRequestDto)).build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRequestDto authRegisterRequest) {
        return ResponseEntity.ok(authService.register(authRegisterRequest));
    }
}
