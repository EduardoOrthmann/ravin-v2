package com.example.ravin.domains.person.custom_validation;

import com.example.ravin.utils.constants.ErrorMessages;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCpfValidator.class)
@Documented
public @interface UniqueCpf {
    String message() default ErrorMessages.CPF_ALREADY_EXISTS;

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
