package mbd.dev.example.controller.auth.dto;

import lombok.Data;
import mbd.dev.example.model.user.User;

@Data

public class SignedDto {

    private User user;
    private String token;
}
