package mbd.dev.example.controller.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class SignDto {

    @NotBlank(message = "EL email es requerido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    private String password;
}
