package com.example.ravin.domains.person.employee;

import com.example.ravin.common_interfaces.CRUD;
import com.example.ravin.domains.dtos.mapper.EmployeeMapper;
import com.example.ravin.domains.dtos.request.EmployeeRequestDto;
import com.example.ravin.domains.dtos.response.EmployeeResponseDto;
import com.example.ravin.domains.person.AbstractPersonService;
import com.example.ravin.domains.auth.user.UserService;
import com.example.ravin.utils.constants.ErrorMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService extends AbstractPersonService implements CRUD<EmployeeRequestDto, EmployeeResponseDto, UUID>, IEmployee<EmployeeResponseDto> {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, UserService userService, EmployeeMapper mapper) {
        super(employeeRepository, userService);
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<EmployeeResponseDto> findAll() {
        return mapper.toResponseList(employeeRepository.findAll());
    }

    @Override
    public EmployeeResponseDto findById(UUID id) {
        return employeeRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.EMPLOYEE_NOT_FOUND));
    }

    @Override
    public EmployeeResponseDto save(EmployeeRequestDto request) {
        super.validate(request);

        Employee employee = mapper.toEntity(request);

        employee.getUser().setRole(request.getPosition().getRole());

        return mapper.toResponse(
                employeeRepository.save(employee)
        );
    }

    @Override
    public EmployeeResponseDto update(UUID id, EmployeeRequestDto request) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.EMPLOYEE_NOT_FOUND));

        super.validate(request, id);

        employee.getUser().setRole(request.getPosition().getRole());

        return mapper.toResponse(
                employeeRepository.save(mapper.updateEntity(employee, request))
        );
    }

    @Override
    public void deleteById(UUID id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessages.EMPLOYEE_NOT_FOUND);
        }

        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeResponseDto> findAllAvailableWaiters() {
        return mapper.toResponseList(employeeRepository.findAllAvailableWaiters());
    }
}
