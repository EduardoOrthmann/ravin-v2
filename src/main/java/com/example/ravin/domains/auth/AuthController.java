package com.example.ravin.domains.auth;

import com.example.ravin.domains.customer.CustomerService;
import com.example.ravin.domains.dtos.request.CustomerRequestDto;
import com.example.ravin.domains.dtos.request.LoginRequestDto;
import com.example.ravin.domains.dtos.response.CustomerResponseDto;
import com.example.ravin.utils.constants.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginRequestDto request) {
        return ResponseEntity.ok().header(Constants.HEADER_STRING, authService.login(request)).build();
    }

    @PostMapping({"/register/customer", "/register"})
    public ResponseEntity<CustomerResponseDto> registerCustomer(@RequestBody @Valid CustomerRequestDto request) {
        return ResponseEntity.ok(customerService.save(request));
    }
}
