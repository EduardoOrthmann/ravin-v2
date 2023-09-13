package com.example.ravin.domains.employee;

import com.example.ravin.domains.person.Person;
import com.example.ravin.enums.EducationLevel;
import com.example.ravin.enums.MaritalStatus;
import com.example.ravin.enums.Position;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Person {
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    @Column(nullable = false)
    private LocalDate admissionDate;

    @Column(insertable = false)
    private LocalDate resignationDate;

    @Column(nullable = false)
    private boolean isAvailable = true;

    @PrePersist
    public void prePersist() {
        if (admissionDate == null) admissionDate = LocalDate.now();
    }
}
