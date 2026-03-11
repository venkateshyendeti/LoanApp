package API_Practice.com.example.demo.Service;

import API_Practice.com.example.demo.Entity.User;
import API_Practice.com.example.demo.Repository.UserRepositry;
import API_Practice.com.example.demo.dto.SignupRequestdto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class Signupimpl {

    private  final PasswordEncoder passwordEncoder;
    private final UserRepositry userRepositry;

    public Signupimpl(PasswordEncoder passwordEncoder, UserRepositry userRepositry) {
        this.passwordEncoder = passwordEncoder;
        this.userRepositry = userRepositry;
    }

      public String register(SignupRequestdto signup){
            if (userRepositry.findByEmail(signup.getEmail()).isPresent()){
                throw new RuntimeException("Email already exist");
            }

            User user = User.builder()
                    .fullname(signup.getFullname())
                    .email(signup.getEmail())
                    .dateOfBirth(signup.getDateOfBirth())
                    .password(passwordEncoder.encode(signup.getPassword()))
                    .gender(signup.getGender())
                    .createdBy(LocalDateTime.now())

                    .build();

            userRepositry.save(user);
            return "User Registerd Sucessfully";
      }
}
