package mbd.dev.example.service.auth;

import lombok.RequiredArgsConstructor;
import mbd.dev.example.controller.auth.dto.SignDto;
import mbd.dev.example.controller.auth.dto.SignedDto;
import mbd.dev.example.controller.user.dto.UserDto;
import mbd.dev.example.model.role.Role;
import mbd.dev.example.model.role.RoleRepository;
import mbd.dev.example.model.user.User;
import mbd.dev.example.model.user.UserRepository;
import mbd.dev.example.security.jwt.JwtProvider;
import mbd.dev.example.security.service.UserDetailsService;
import mbd.dev.example.service.user.UserService;
import mbd.dev.example.utils.CustomResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;
    private final UserService userService;


    @Transactional(rollbackFor = SQLException.class)
    public CustomResponse<User> create (UserDto user) {
        Role role = roleRepository.findById(user.getIdRole()).orElse(null);
        if (role == null) {
            return new CustomResponse<>(
                    null, false, 404, "Rol no encontrado"
            );
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(role);
        newUser = userRepository.save(newUser);
        return new CustomResponse<>(
                newUser, true, 201, "Usuario creado"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<SignedDto> login (SignDto user) {
        Optional<User> foundUser = userService.findByEmail(user.getEmail());
        if (foundUser.isEmpty()) {
            return new CustomResponse<>(
                    null, false, 404, "Email no encontrado"
            );
        }
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        var userFound = userRepository.findByEmail(user.getEmail())
                .orElseThrow();
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String jwtToken = jwtProvider.generateToken(userDetails);
        SignedDto signedDto = new SignedDto();
        signedDto.setUser(userFound);
        signedDto.setToken(jwtToken);
        return new CustomResponse<>(
                signedDto, false, 200, "Usuario logueado correctamente!"
        );
    }

}
