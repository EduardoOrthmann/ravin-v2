package com.example.ravin.domains.employee;

import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmployeeEntityListener {
    @PrePersist
    public void prePersist(Employee employee) {
        if (employee.getAdmissionDate() == null) employee.setAdmissionDate(LocalDate.now());
        employee.setAvailable(true);
    }
}
