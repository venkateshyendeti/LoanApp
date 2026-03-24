package API_Practice.com.example.demo.Service.usermangment;

import API_Practice.com.example.demo.Entity.UserManegment;
import API_Practice.com.example.demo.dto.Usermanegmentdto;

public interface CreateUser {

    Usermanegmentdto getUserById(Long id);
    String updateUser(Long id, Usermanegmentdto dto);
}
