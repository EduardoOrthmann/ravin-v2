package com.example.ravin.domains.person.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Embeddable;
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
    private String neighborhood;
    private String street;

    @Size(min = 1, max = 10, message = "O número deve ter entre 1 e 10 caracteres")
    private String number;

    private String complement;
}
