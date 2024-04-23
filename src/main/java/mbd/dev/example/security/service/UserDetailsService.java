package mbd.dev.example.security.service;

import lombok.RequiredArgsConstructor;
import mbd.dev.example.model.user.User;
import mbd.dev.example.security.model.UserDetailsImpl;
import mbd.dev.example.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = service.findByEmail(email);
        if (user.isPresent()) {
            return UserDetailsImpl.build(user.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
