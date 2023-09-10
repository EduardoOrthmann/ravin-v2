package com.example.ravin.domains.dtos.request;

import com.example.ravin.domains.person.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto extends PersonRequestDto {
    private Address address;
}