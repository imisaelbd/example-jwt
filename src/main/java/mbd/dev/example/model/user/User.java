package mbd.dev.example.model.user;

import jakarta.persistence.*;
import lombok.Data;
import mbd.dev.example.model.role.Role;

@Entity
@Table(name = "users")
@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
}
