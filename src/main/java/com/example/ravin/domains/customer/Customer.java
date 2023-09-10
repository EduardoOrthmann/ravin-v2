package com.example.ravin.domains.customer;

import com.example.ravin.domains.person.address.Address;
import com.example.ravin.domains.person.Person;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private Address address;
}
