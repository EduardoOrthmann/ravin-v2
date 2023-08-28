package com.example.ravin.domains.auth;

import com.example.ravin.dtos.mapper.UserMapper;
import com.example.ravin.dtos.request.UserRequestDto;
import com.example.ravin.dtos.request.LoginRequestDto;
import com.example.ravin.dtos.response.UserResponseDto;
import com.example.ravin.exceptions.UserAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserMapper mapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok().header("Authorization", authService.login(loginRequestDto)).build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRequestDto authRegisterRequest) {
        return ResponseEntity.ok(mapper.toResponse(
                        authService.register(mapper.toEntity(authRegisterRequest))
                ));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
