package mbd.dev.example.controller.auth;

import lombok.RequiredArgsConstructor;
import mbd.dev.example.controller.auth.dto.SignDto;
import mbd.dev.example.controller.auth.dto.SignedDto;
import mbd.dev.example.controller.user.dto.UserDto;
import mbd.dev.example.model.user.User;
import mbd.dev.example.service.auth.AuthService;
import mbd.dev.example.utils.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    @PostMapping("/create")
    public ResponseEntity<CustomResponse<User>> create(@RequestBody UserDto user) {
        try {
            return ResponseEntity.ok(authService.create(user));
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new CustomResponse<>(
                            null, false, 500, "Error interno del servidor"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/login")
    public ResponseEntity<CustomResponse<SignedDto>> login(@RequestBody SignDto signDto) {
        try {
            return ResponseEntity.ok(authService.login(signDto));
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new CustomResponse<>(
                            null, false, 500, "Error interno del servidor"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
