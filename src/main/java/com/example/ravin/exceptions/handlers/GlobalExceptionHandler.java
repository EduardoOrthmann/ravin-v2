package com.example.ravin.exceptions.handlers;

import com.example.ravin.domains.dtos.response.ErrorDto;
import com.example.ravin.domains.dtos.response.FieldErrorDto;
import com.example.ravin.exceptions.JwtSecurityException;
import com.example.ravin.utils.constants.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(
                ErrorDto.builder()
                        .message(ErrorMessages.VALIDATION_ERROR)
                        .status(HttpStatus.BAD_REQUEST.name())
                        .errors(
                                ex.getBindingResult().getFieldErrors().stream()
                                        .map(fieldError -> new FieldErrorDto(fieldError.getField(), fieldError.getDefaultMessage()))
                                        .toList()
                        )
                        .build()
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorDto> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.UNAUTHORIZED.toString())
                        .build()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorDto> handleAccessDeniedException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                ErrorDto.builder()
                        .message(ErrorMessages.UNSUFFICIENT_CREDENTIALS)
                        .status(HttpStatus.FORBIDDEN.toString())
                        .build()
        );
    }

    @ExceptionHandler({JwtSecurityException.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorDto> handleJwtSecurityException(JwtSecurityException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.UNAUTHORIZED.toString())
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .build()
        );
    }
}
