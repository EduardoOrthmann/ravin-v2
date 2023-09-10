package com.example.ravin.domains.dtos.response;

import com.example.ravin.enums.EducationLevel;
import com.example.ravin.enums.MaritalStatus;
import com.example.ravin.enums.Position;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto extends PersonResponseDto {
    @JsonProperty("marital_status")
    private MaritalStatus maritalStatus;

    @JsonProperty("education_level")
    private EducationLevel educationLevel;

    private Position position;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("admission_date")
    private LocalDate admissionDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("resignation_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate resignationDate;

    @JsonProperty("is_available")
    private boolean isAvailable;
}
