package com.example.ravin.domains.auth;

import com.example.ravin.domains.customer.Customer;
import com.example.ravin.domains.customer.CustomerService;
import com.example.ravin.domains.dtos.mapper.CustomerMapper;
import com.example.ravin.domains.dtos.mapper.EmployeeMapper;
import com.example.ravin.domains.dtos.request.CustomerRequestDto;
import com.example.ravin.domains.dtos.request.EmployeeRequestDto;
import com.example.ravin.domains.dtos.response.CustomerResponseDto;
import com.example.ravin.domains.dtos.response.EmployeeResponseDto;
import com.example.ravin.domains.employee.EmployeeService;
import com.example.ravin.domains.user.User;
import com.example.ravin.domains.dtos.request.LoginRequestDto;
import com.example.ravin.enums.UserRole;
import com.example.ravin.utils.JwtUtils;
import com.example.ravin.utils.constants.ErrorMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public String login(LoginRequestDto loginRequestDto) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getLogin(), loginRequestDto.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            throw new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }

        return jwtUtils.generateToken((User) authentication.getPrincipal());
    }

    public CustomerResponseDto registerCustomer(CustomerRequestDto request) {
        Customer customer = customerMapper.toEntity(request);

        customer.getUser().setRole(UserRole.USER);

        return customerService.save(customerMapper.toRequest(customer));
    }

    public EmployeeResponseDto registerEmployee(EmployeeRequestDto request) {
        return employeeService.save(employeeMapper.toRequest(employeeMapper.toEntity(request)));
    }
}
