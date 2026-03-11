package API_Practice.com.example.demo.Controller;

import API_Practice.com.example.demo.Service.Signupimpl;
import API_Practice.com.example.demo.dto.SignupRequestdto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/api")
public class SignupController {

     private final Signupimpl singSignupimpl;

    public SignupController(Signupimpl singSignupimpl) {
        this.singSignupimpl = singSignupimpl;
    }

    @PostMapping("/register")
public ResponseEntity<String> register (@RequestBody SignupRequestdto requestdto){
        String response = singSignupimpl.register(requestdto);
        return ResponseEntity.ok(response);
}
}
