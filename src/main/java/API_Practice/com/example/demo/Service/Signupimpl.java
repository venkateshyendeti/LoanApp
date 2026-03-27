package API_Practice.com.example.demo.Service;

import API_Practice.com.example.demo.Entity.User;
import API_Practice.com.example.demo.Repository.UserRepositry;
import API_Practice.com.example.demo.dto.Logindto;
import API_Practice.com.example.demo.dto.SignupRequestdto;
import API_Practice.com.example.demo.exception.InvalidCredentialsException;
import API_Practice.com.example.demo.jwtutil.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
public class Signupimpl {

    private  final PasswordEncoder passwordEncoder;
    private final UserRepositry userRepositry;
    private final JwtToken jwtToken;

    public Signupimpl(PasswordEncoder passwordEncoder, UserRepositry userRepositry,JwtToken jwtToken) {
        this.passwordEncoder = passwordEncoder;
        this.userRepositry = userRepositry;
        this.jwtToken=jwtToken;
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

    public Map<String,String> login(Logindto request){

        User user = userRepositry.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid password");
        }
        String accessToken = jwtToken.generateToken(user.getEmail());
        String refreshToken = jwtToken.generateRefreshToken(user.getEmail());

        log.info("accessToken:",accessToken);
        log.info("refershToken:",refreshToken);

        return Map.of(
                "accessToken",accessToken,
                "refreshToken",refreshToken
        );
    }
}
