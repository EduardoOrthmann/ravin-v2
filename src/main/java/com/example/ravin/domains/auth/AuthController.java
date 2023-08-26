package com.example.ravin.domains.auth;

import com.example.ravin.domains.user.User;
import com.example.ravin.dtos.AuthRegisterRequestDto;
import com.example.ravin.dtos.AuthRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequestDto authRequest) {
        return authService.login(authRequest)
                .map(auth -> ResponseEntity.ok().header("Authorization", auth).build())
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid AuthRegisterRequestDto authRegisterRequest) {
        User user = authService.register(authRegisterRequest);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user);
    }
}
