package API_Practice.com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SignupRequestdto {

private String fullname;
private String email;
private String gender;
private LocalDate dateOfBirth;
private String password;


}
