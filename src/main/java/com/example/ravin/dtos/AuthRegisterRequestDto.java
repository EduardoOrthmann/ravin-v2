package com.example.ravin.dtos;

import com.example.ravin.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AuthRegisterRequestDto {
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
