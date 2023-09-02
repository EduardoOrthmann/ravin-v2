package com.example.ravin.domains.customer;

import com.example.ravin.domains.address.Address;
import com.example.ravin.domains.person.Person;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Person {
    @Embedded
    @Valid
    private Address address;
}
