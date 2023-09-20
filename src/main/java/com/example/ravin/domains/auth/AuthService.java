package com.example.ravin.domains.auth;

import com.example.ravin.domains.dtos.request.CustomerRequestDto;
import com.example.ravin.domains.dtos.request.EmployeeRequestDto;
import com.example.ravin.domains.dtos.request.LoginRequestDto;
import com.example.ravin.domains.dtos.response.CustomerResponseDto;
import com.example.ravin.domains.dtos.response.EmployeeResponseDto;

public interface AuthService {
    String login(LoginRequestDto loginRequestDto);

    CustomerResponseDto registerCustomer(CustomerRequestDto request);

    EmployeeResponseDto registerEmployee(EmployeeRequestDto request);
}
