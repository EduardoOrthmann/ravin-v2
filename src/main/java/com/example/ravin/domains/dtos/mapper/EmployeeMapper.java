package com.example.ravin.domains.dtos.mapper;

import com.example.ravin.domains.dtos.request.EmployeeRequestDto;
import com.example.ravin.domains.dtos.response.EmployeeResponseDto;
import com.example.ravin.domains.person.employee.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper extends AbstractMapper<Employee, EmployeeRequestDto, EmployeeResponseDto> {
    public EmployeeMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    Class<Employee> getEntityClass() {
        return Employee.class;
    }

    @Override
    Class<EmployeeRequestDto> getRequestClass() {
        return EmployeeRequestDto.class;
    }

    @Override
    Class<EmployeeResponseDto> getResponseClass() {
        return EmployeeResponseDto.class;
    }
}
