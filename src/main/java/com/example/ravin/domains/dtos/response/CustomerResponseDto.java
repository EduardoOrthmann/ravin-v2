package com.example.ravin.domains.dtos.response;

import com.example.ravin.domains.person.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto extends PersonResponseDto {
    private Address address;
}
