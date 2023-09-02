package com.example.ravin.domains.user.custom_validation;

import com.example.ravin.utils.constants.ErrorMessages;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLoginValidator.class)
@Documented
public @interface UniqueLogin {
    String message() default ErrorMessages.USER_ALREADY_EXISTS;

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
