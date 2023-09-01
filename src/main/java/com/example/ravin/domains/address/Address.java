package com.example.ravin.domains.address;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
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
    private String number;
    private String complement;
}
