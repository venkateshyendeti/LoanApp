package API_Practice.com.example.demo.Service.usermangment;

import API_Practice.com.example.demo.Entity.UserManegment;
import API_Practice.com.example.demo.Repository.UserMangRepo;
import API_Practice.com.example.demo.dto.Usermanegmentdto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CreateUserIml {

    private final PasswordEncoder passwordEncoder;
    private final UserMangRepo userMangRepo;

    public CreateUserIml(PasswordEncoder passwordEncoder, UserMangRepo userMangRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userMangRepo = userMangRepo;
    }
    public String createNew(Usermanegmentdto usermanegmentdto){
        UserManegment user = UserManegment.builder()
                .accountType(usermanegmentdto.getAccountType())
                .accountStatus(usermanegmentdto.getAccountStatus())
                .firstName(usermanegmentdto.getFirstName())
                .lastName(usermanegmentdto.getLastName())
                .email(usermanegmentdto.getEmail())
                .mobileNumber(usermanegmentdto.getMobile())
                .password(usermanegmentdto.getPassword()) // later encode చేయాలి
                .enabled(usermanegmentdto.getEnabled())
                .build();

        userMangRepo.save(user);

        return "User Created Successfully";
    }

    public List<UserManegment> getVerifiedUsers() {
        return userMangRepo.findByAccountStatus("verified");
    }

    public UserManegment getUserById(Long id) {
        return userMangRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }


    public String updateUser(Long id, Usermanegmentdto dto) {

        UserManegment user = userMangRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setAccountType(dto.getAccountType());
        user.setAccountStatus(dto.getAccountStatus());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setMobileNumber(dto.getMobile());
      //  user.setPassword(dto.getPassword()); // later encode
        user.setEnabled(dto.getEnabled());

        userMangRepo.save(user);

        return "User Updated Successfully";
    }

    public String deleteById(Long id) {

        UserManegment user = userMangRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        userMangRepo.deleteById(id);

        return "User deleted successfully";
    }


}
