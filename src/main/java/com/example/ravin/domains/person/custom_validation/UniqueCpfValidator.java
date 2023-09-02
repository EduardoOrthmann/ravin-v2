package com.example.ravin.domains.person.custom_validation;

import com.example.ravin.domains.person.PersonRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueCpfValidator implements ConstraintValidator<UniqueCpf, String> {
    private final PersonRepository personRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !personRepository.existsByCpf(s);
    }
}
