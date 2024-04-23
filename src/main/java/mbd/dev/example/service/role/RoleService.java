package mbd.dev.example.service.role;

import lombok.RequiredArgsConstructor;
import mbd.dev.example.controller.role.dto.RoleDto;
import mbd.dev.example.model.role.Role;
import mbd.dev.example.model.role.RoleRepository;
import mbd.dev.example.utils.CustomResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional
@RequiredArgsConstructor

public class RoleService {

    private final RoleRepository repository;

    @Transactional(rollbackFor = SQLException.class)
    public CustomResponse<Role> create (RoleDto role) {
        if (repository.existsByName(role.getName())) {
            return new CustomResponse<>(
                    null, false, 400, "Rol ya existe"
            );
        }
        Role newRole = new Role();
        newRole.setName(role.getName());
        newRole = repository.save(newRole);
        return new CustomResponse<>(
                newRole, true, 201, "Rol creado"
        );
    }
}
