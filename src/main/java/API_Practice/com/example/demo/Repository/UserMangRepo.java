package API_Practice.com.example.demo.Repository;

import API_Practice.com.example.demo.Entity.UserManegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMangRepo extends JpaRepository<UserManegment,Long> {
    List<UserManegment> findByAccountStatus(String account_status);
}
