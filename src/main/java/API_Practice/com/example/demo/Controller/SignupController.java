package API_Practice.com.example.demo.Controller;

import API_Practice.com.example.demo.Service.Signupimpl;
import API_Practice.com.example.demo.dto.Logindto;
import API_Practice.com.example.demo.dto.SignupRequestdto;
import API_Practice.com.example.demo.jwtutil.JwtToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth/api")
public class SignupController {

     private final Signupimpl singSignupimpl;
     private final JwtToken jwtToken;

    public SignupController(Signupimpl singSignupimpl,JwtToken jwtToken) {
        this.singSignupimpl = singSignupimpl;
        this.jwtToken=jwtToken;
    }

    @PostMapping("/register")
public ResponseEntity<String> register (@ModelAttribute SignupRequestdto requestdto){
        String response = singSignupimpl.register(requestdto);
        return ResponseEntity.ok(response);
}

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Logindto request){
try {
    Map<String, String> tokens = singSignupimpl.login(request);
    return ResponseEntity.ok(tokens);
}catch (Exception e) {
    return ResponseEntity.status(401).body(e.getMessage());
}
    }
}
