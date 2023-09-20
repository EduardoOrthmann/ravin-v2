package com.example.ravin.domains.person.employee;

import com.example.ravin.common_interfaces.CRUD;
import com.example.ravin.domains.dtos.request.EmployeeRequestDto;
import com.example.ravin.domains.dtos.response.EmployeeResponseDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService extends CRUD<EmployeeRequestDto, EmployeeResponseDto, UUID> {
    List<EmployeeResponseDto> findAllAvailableWaiters();
}
