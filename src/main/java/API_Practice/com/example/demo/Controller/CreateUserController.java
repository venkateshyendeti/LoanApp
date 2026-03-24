package API_Practice.com.example.demo.Controller;

import API_Practice.com.example.demo.Entity.UserManegment;
import API_Practice.com.example.demo.Service.usermangment.CreateUserIml;
import API_Practice.com.example.demo.dto.Usermanegmentdto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/create")
@CrossOrigin(origins = "*")
public class CreateUserController {
    private final CreateUserIml createUserIml;

    public CreateUserController(CreateUserIml createUserIml) {
        this.createUserIml = createUserIml;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createNew(@RequestBody @Valid Usermanegmentdto usermanegmentdto){
        String response = createUserIml.createNew(usermanegmentdto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verified")
    public ResponseEntity<List<UserManegment>> getVerifiedUsers() {
        List<UserManegment> users = createUserIml.getVerifiedUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserManegment> getUser(@PathVariable Long id){
        UserManegment user = createUserIml.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestBody Usermanegmentdto dto) {

        String response = createUserIml.updateUser(id, dto);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteUser(@PathVariable Long id){
        createUserIml.deleteById(id);
        return ResponseEntity.ok("user deleted successfully");

}

}
