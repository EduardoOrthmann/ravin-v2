package com.example.ravin.domains.dtos.request;

import com.example.ravin.domains.user.custom_validation.UniqueLogin;
import com.example.ravin.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotNull(message = "O login não pode ser nulo")
    @NotBlank(message = "O login não pode estar em branco")
    @Size(min = 3, max = 255, message = "O login deve ter entre 3 e 255 caracteres")
    @UniqueLogin(message = "O login já está em uso")
    private String login;

    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 3, max = 255, message = "A senha deve ter entre 3 e 255 caracteres")
    private String password;

    @NotNull(message = "A função não pode ser nula")
    private UserRole role;
}
