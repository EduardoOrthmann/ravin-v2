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
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@DynamicInsert
@DynamicUpdate
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
    @ColumnDefault("CURRENT_DATE")
    private LocalDate admissionDate = LocalDate.now();

    @Column(insertable = false)
    private LocalDate resignationDate;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean isAvailable = true;
}
