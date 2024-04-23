package mbd.dev.example.controller.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class UserDto {

    @NotBlank(message = "EL email es requerido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    private String password;

    @NotBlank(message = "El rol es requerido")
    private Long idRole;
}
