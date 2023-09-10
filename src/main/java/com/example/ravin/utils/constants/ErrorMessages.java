package com.example.ravin.utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessages {
    // JWT Error Messages
    public final String JWT_CREATION_ERROR = "Erro ao gerar o token";
    public final String TOKEN_NOT_FOUND = "O token não foi encontrado";

    // Authentication Error Messages
    public final String UNSUFFICIENT_CREDENTIALS = "Credenciais insuficientes";

    // User Error Messages
    public final String USER_NOT_FOUND = "O usuário não foi encontrado";
    public final String USERNAME_NOT_FOUND = "O nome de usuário não foi encontrado";
    public final String LOGIN_ALREADY_EXISTS = "O login já existe";

    // Person Error Messages
    public final String CPF_ALREADY_EXISTS = "CPF já cadastrado!";

    // Customer Error Messages
    public final String CUSTOMER_NOT_FOUND = "O cliente não foi encontrado";

    // Employee Error Messages
    public final String EMPLOYEE_NOT_FOUND = "O funcionário não foi encontrado";

    // Validation Error Messages
    public final String VALIDATION_ERROR = "Erro de validação";
}
