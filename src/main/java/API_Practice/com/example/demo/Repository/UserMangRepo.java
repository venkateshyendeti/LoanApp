package API_Practice.com.example.demo.Repository;

import API_Practice.com.example.demo.Entity.User;
import API_Practice.com.example.demo.Entity.UserManegment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMangRepo extends JpaRepository<UserManegment,Long> {
    Page<UserManegment> findByAccountStatus(String account_status, Pageable pageable);

    Page<UserManegment> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String status,
            String firstName,
            String lastName,
            String email,
            Pageable pageable
    );

    Page<UserManegment> findByAccountStatusAndFirstNameContainingIgnoreCaseOrAccountStatusAndLastNameContainingIgnoreCaseOrAccountStatusAndEmailContainingIgnoreCase(
            String status1, String firstName,
            String status2, String lastName,
            String status3, String email,
            Pageable pageable
    );
}
