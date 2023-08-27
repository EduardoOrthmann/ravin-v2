package com.example.ravin.exceptions;

public class JwtSecurityException extends RuntimeException {
    public JwtSecurityException(String message) {
        super(message);
    }

    public JwtSecurityException() {
        super("O token informado é inválido ou está expirado");
    }
}
