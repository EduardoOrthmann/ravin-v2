package com.example.ravin.domains.auth;

import com.example.ravin.domains.dtos.request.CustomerRequestDto;
import com.example.ravin.domains.dtos.request.EmployeeRequestDto;
import com.example.ravin.domains.dtos.request.LoginRequestDto;
import com.example.ravin.domains.dtos.response.CustomerResponseDto;
import com.example.ravin.domains.dtos.response.EmployeeResponseDto;
import com.example.ravin.utils.constants.Constants;
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

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginRequestDto request) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).header(Constants.HEADER_STRING, authService.login(request)).build();
    }

    @PostMapping({"/register/customer", "/register"})
    public ResponseEntity<CustomerResponseDto> registerCustomer(@RequestBody @Valid CustomerRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerCustomer(request));
    }

    @PostMapping("/register/employee")
    public ResponseEntity<EmployeeResponseDto> registerEmployee(@RequestBody @Valid EmployeeRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerEmployee(request));
    }
}
