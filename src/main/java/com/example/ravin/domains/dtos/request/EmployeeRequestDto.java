package com.example.ravin.domains.dtos.request;

import com.example.ravin.enums.EducationLevel;
import com.example.ravin.enums.MaritalStatus;
import com.example.ravin.enums.Position;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto extends PersonRequestDto {
    @JsonProperty("marital_status")
    private MaritalStatus maritalStatus;

    @JsonProperty("education_level")
    private EducationLevel educationLevel;

    @NotNull(message = "O cargo não pode ser nulo")
    private Position position;

    @PastOrPresent(message = "A data de admissão deve ser anterior ou igual a data atual")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("admission_date")
    private LocalDate admissionDate;
}
