package mbd.dev.example.service.user;

import lombok.RequiredArgsConstructor;
import mbd.dev.example.model.user.User;
import mbd.dev.example.model.user.UserRepository;
import mbd.dev.example.utils.CustomResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class UserService {

    private final UserRepository repository;

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<User>> getAll () {
        List<User> users = repository.findAll();
        if (users.isEmpty()) {
            return new CustomResponse<>(
                    users, false, 404, "No hay usuarios registrados"
            );
        } else {
            return new CustomResponse<>(
                    users, true, 200, "Usuarios encontrados"
            );
        }
    }

    @Transactional(readOnly = true)
    public CustomResponse<User> getById(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return new CustomResponse<>(
                    user.get(), true, 200, "Usuario encontrado"
            );
        } else {
            return new CustomResponse<>(
                    null, false, 404, "Usuario no encontrado"
            );
        }
    }

}
