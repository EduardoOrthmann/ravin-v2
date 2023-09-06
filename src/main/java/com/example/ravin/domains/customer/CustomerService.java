package com.example.ravin.domains.customer;

import com.example.ravin.common_interfaces.CRUD;
import com.example.ravin.domains.dtos.mapper.CustomerMapper;
import com.example.ravin.domains.dtos.request.CustomerRequestDto;
import com.example.ravin.domains.dtos.response.CustomerResponseDto;
import com.example.ravin.domains.user.UserService;
import com.example.ravin.exceptions.CpfAlreadyExistsException;
import com.example.ravin.exceptions.LoginAlreadyExists;
import com.example.ravin.utils.constants.ErrorMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService implements CRUD<CustomerRequestDto, CustomerResponseDto, UUID> {
    private final CustomerRepository customerRepository;
//    private final UserService userService;
    private final CustomerMapper mapper;

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
//        if (customerRepository.existsByCpf(request.getCpf())) {
//            throw new CpfAlreadyExistsException();
//        }
//
//        if (userService.existsByLogin(request.getUser().getLogin())) {
//            throw new LoginAlreadyExists();
//        }

        return mapper.toResponse(
                customerRepository.save(mapper.toEntity(request))
        );
    }

    @Override
    public CustomerResponseDto update(UUID id, CustomerRequestDto request) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND);
        }

//        if (customerRepository.existsByCpf(request.getCpf()) && !customer.get().getCpf().equals(request.getCpf())) {
//            throw new CpfAlreadyExistsException();
//        }
//
//        if (userService.existsByLogin(request.getUser().getLogin()) && !customer.get().getUser().getLogin().equals(request.getUser().getLogin())) {
//            throw new LoginAlreadyExists();
//        }

        return mapper.toResponse(
                customerRepository.save(mapper.updateEntity(customer.get(), request))
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
