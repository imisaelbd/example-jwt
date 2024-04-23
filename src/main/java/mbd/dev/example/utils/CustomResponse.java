package mbd.dev.example.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomResponse <T> {
    private T data;
    private boolean error;
    private int status;
    private String message;

}
