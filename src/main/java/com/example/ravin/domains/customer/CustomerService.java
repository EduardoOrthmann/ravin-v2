package com.example.ravin.domains.customer;

import com.example.ravin.common_interfaces.CRUD;
import com.example.ravin.domains.dtos.mapper.CustomerMapper;
import com.example.ravin.domains.dtos.request.CustomerRequestDto;
import com.example.ravin.domains.dtos.response.CustomerResponseDto;
import com.example.ravin.domains.person.AbstractPersonService;
import com.example.ravin.domains.user.UserService;
import com.example.ravin.utils.constants.ErrorMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService extends AbstractPersonService implements CRUD<CustomerRequestDto, CustomerResponseDto, UUID> {
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository customerRepository, UserService userService, CustomerMapper mapper) {
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

        return mapper.toResponse(
                customerRepository.save(mapper.toEntity(request))
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
