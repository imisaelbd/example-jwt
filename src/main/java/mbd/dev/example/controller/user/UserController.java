package mbd.dev.example.controller.user;

import lombok.RequiredArgsConstructor;
import mbd.dev.example.model.user.User;
import mbd.dev.example.service.user.UserService;
import mbd.dev.example.utils.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<CustomResponse<List<User>>> getAll () {
        try {
            return ResponseEntity.ok(userService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new CustomResponse<>(
                            null, false, 500, "Error interno del servidor"
                    )
            );
        }
    }
 }
