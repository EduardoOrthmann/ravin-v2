package com.example.ravin.domains.person.customer;

import com.example.ravin.common_interfaces.CRUD;
import com.example.ravin.domains.dtos.request.CustomerRequestDto;
import com.example.ravin.domains.dtos.response.CustomerResponseDto;

import java.util.UUID;

public interface CustomerService extends CRUD<CustomerRequestDto, CustomerResponseDto, UUID> {
}
