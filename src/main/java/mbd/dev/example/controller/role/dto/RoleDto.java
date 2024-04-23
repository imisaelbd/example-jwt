package mbd.dev.example.controller.role.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class RoleDto {

    @NotBlank(message = "El nombre es requerido")
    private String name;
}
