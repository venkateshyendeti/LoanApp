package API_Practice.com.example.demo.Controller;

import API_Practice.com.example.demo.Entity.User;
import API_Practice.com.example.demo.Entity.UserManegment;
import API_Practice.com.example.demo.Service.usermangment.CreateUserIml;
import API_Practice.com.example.demo.dto.Usermanegmentdto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

   /* @GetMapping("/verified")
    public ResponseEntity<Map<String, Object>> getVerifiedUsers(@RequestParam(required = false) String status,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        Page<UserManegment> userPage = createUserIml.getVerifiedUsers(status, page, size);
        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("currentPage", userPage.getNumber());
        response.put("totalPages", userPage.getTotalPages());

        return ResponseEntity.ok(response);

    }*/

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



    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getUsers(
            @RequestParam(defaultValue = "verified") String status,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<UserManegment> userPage = createUserIml.getUsers(status,search, page, size, sortBy);

        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("currentPage", userPage.getNumber());
        response.put("totalItems", userPage.getTotalElements());
        response.put("totalPages", userPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

}
