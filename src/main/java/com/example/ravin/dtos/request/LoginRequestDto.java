package com.example.ravin.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {
    @NotNull
    @NotBlank
    private String login;

    @NotNull
    @NotBlank
    private String password;
}
