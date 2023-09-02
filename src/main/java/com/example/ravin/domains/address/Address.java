package com.example.ravin.domains.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Embeddable
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String country;
    private String state;
    private String city;
    private String zipCode;

    @NotNull(message = "O bairro não pode ser nulo")
    @NotBlank(message = "O bairro não pode estar em branco")
    private String neighborhood;

    @NotNull(message = "A rua não pode ser nula")
    @NotBlank(message = "A rua não pode estar em branco")
    private String street;

    @NotNull(message = "O número não pode ser nulo")
    @NotBlank(message = "O número não pode estar em branco")
    @Size(min = 1, max = 10, message = "O número deve ter entre 1 e 10 caracteres")
    private String number;

    private String complement;
}
