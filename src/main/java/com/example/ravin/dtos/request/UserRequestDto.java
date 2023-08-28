package com.example.ravin.dtos.request;

import com.example.ravin.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String login;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String password;

    @NotNull
    private UserRole role;
}
