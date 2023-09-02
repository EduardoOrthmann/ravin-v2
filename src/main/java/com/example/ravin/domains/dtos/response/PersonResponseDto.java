package com.example.ravin.domains.dtos.response;

import com.example.ravin.domains.dtos.custom_serializer.CpfSerializer;
import com.example.ravin.domains.dtos.custom_serializer.PhoneNumberSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDto {
    private UUID id;
    private String name;

    @JsonProperty("phone_number")
    @JsonSerialize(using = PhoneNumberSerializer.class)
    private String phoneNumber;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @JsonSerialize(using = CpfSerializer.class)
    private String cpf;

    private boolean isActive;
    private UserResponseDto user;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonProperty("created_date")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonProperty("last_modified_date")
    private LocalDateTime lastModifiedDate;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("last_modified_by")
    private String lastModifiedBy;
}
