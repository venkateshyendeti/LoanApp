package API_Practice.com.example.demo.Response;

import API_Practice.com.example.demo.Entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterResponse {

    private Integer statuscode;
    private Boolean success;
    private String message;
    private User user;
    private Object error;
    private LocalDateTime timestamp;
}
