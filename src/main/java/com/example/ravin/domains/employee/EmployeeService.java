package com.example.ravin.domains.employee;

import com.example.ravin.common_interfaces.CRUD;
import com.example.ravin.domains.dtos.mapper.EmployeeMapper;
import com.example.ravin.domains.dtos.request.EmployeeRequestDto;
import com.example.ravin.domains.dtos.response.EmployeeResponseDto;
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
public class EmployeeService implements CRUD<EmployeeRequestDto, EmployeeResponseDto, UUID>, IEmployee<EmployeeResponseDto> {
    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final EmployeeMapper mapper;

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
        if (employeeRepository.existsByCpf(request.getCpf())) {
            throw new CpfAlreadyExistsException();
        }

        if (userService.existsByLogin(request.getUser().getLogin())) {
            throw new LoginAlreadyExists();
        }

        Employee employee = mapper.toEntity(request);

        employee.getUser().setRole(request.getPosition().getRole());

        return mapper.toResponse(
                employeeRepository.save(employee)
        );
    }

    @Override
    public EmployeeResponseDto update(UUID id, EmployeeRequestDto request) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessages.EMPLOYEE_NOT_FOUND);
        }

        if (employeeRepository.existsByCpf(request.getCpf()) && !employee.get().getCpf().equals(request.getCpf())) {
            throw new CpfAlreadyExistsException();
        }

        if (userService.existsByLogin(request.getUser().getLogin()) && !employee.get().getUser().getLogin().equals(request.getUser().getLogin())) {
            throw new LoginAlreadyExists();
        }

        employee.get().getUser().setRole(request.getPosition().getRole());

        return mapper.toResponse(
                employeeRepository.save(mapper.updateEntity(employee.get(), request))
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
