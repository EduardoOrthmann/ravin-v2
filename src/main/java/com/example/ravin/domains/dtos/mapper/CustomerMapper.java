package com.example.ravin.domains.dtos.mapper;

import com.example.ravin.domains.customer.Customer;
import com.example.ravin.domains.dtos.request.CustomerRequestDto;
import com.example.ravin.domains.dtos.response.CustomerResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper extends AbstractMapper<Customer, CustomerRequestDto, CustomerResponseDto> {
    public CustomerMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    Class<CustomerRequestDto> getRequestClass() {
        return CustomerRequestDto.class;
    }

    @Override
    Class<CustomerResponseDto> getResponseClass() {
        return CustomerResponseDto.class;
    }
}
