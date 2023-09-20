package com.example.ravin.domains.person.customer.impl;

import com.example.ravin.domains.auth.user.UserService;
import com.example.ravin.domains.dtos.mapper.CustomerMapper;
import com.example.ravin.domains.dtos.request.CustomerRequestDto;
import com.example.ravin.domains.dtos.response.CustomerResponseDto;
import com.example.ravin.domains.person.AbstractPersonService;
import com.example.ravin.domains.person.customer.Customer;
import com.example.ravin.domains.person.customer.CustomerRepository;
import com.example.ravin.domains.person.customer.CustomerService;
import com.example.ravin.enums.UserRole;
import com.example.ravin.utils.constants.ErrorMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl extends AbstractPersonService implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, UserService userService, CustomerMapper mapper) {
        super(customerRepository, userService);
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CustomerResponseDto> findAll() {
        return mapper.toResponseList(customerRepository.findAll());
    }

    @Override
    public CustomerResponseDto findById(UUID id) {
        return customerRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND));
    }

    @Override
    public CustomerResponseDto save(CustomerRequestDto request) {
        super.validate(request);

        Customer customer = mapper.toEntity(request);

        customer.getUser().setRole(UserRole.USER);

        return mapper.toResponse(
                customerRepository.save(customer)
        );
    }

    @Override
    public CustomerResponseDto update(UUID id, CustomerRequestDto request) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND));

        super.validate(request, id);

        return mapper.toResponse(
                customerRepository.save(mapper.updateEntity(customer, request))
        );
    }

    @Override
    public void deleteById(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND);
        }

        customerRepository.deleteById(id);
    }
}
