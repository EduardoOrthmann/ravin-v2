package com.example.ravin.exceptions.handlers;

import com.example.ravin.domains.auth.AuthController;
import com.example.ravin.domains.dtos.response.ErrorDto;
import com.example.ravin.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {AuthController.class})
public class AuthControllerExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.toString())
                        .build()
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.toString())
                        .build()
        );
    }
}
