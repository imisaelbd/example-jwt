package mbd.dev.example.controller.role;

import lombok.RequiredArgsConstructor;
import mbd.dev.example.controller.role.dto.RoleDto;
import mbd.dev.example.model.role.Role;
import mbd.dev.example.service.role.RoleService;
import mbd.dev.example.utils.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor

public class RoleController {
    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<CustomResponse<Role>> create (@RequestBody RoleDto role) {
        try {
            return ResponseEntity.ok(roleService.create(role));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new CustomResponse<>(
                            null, false, 500, "Error interno del servidor"
                    )
            );
        }
    }
}
