package com.example.ravin.utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessages {
    // JWT Error Messages
    public final String JWT_CREATION_ERROR = "Erro ao gerar o token";
    public final String TOKEN_NOT_FOUND = "O token não foi encontrado";

    // User Error Messages
    public final String USER_NOT_FOUND = "O usuário não foi encontrado";
    public final String USERNAME_NOT_FOUND = "O nome de usuário não foi encontrado";

    // Validation Error Messages
    public final String VALIDATION_ERROR = "Erro de validação";
}
