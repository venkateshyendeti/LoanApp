package API_Practice.com.example.demo.Repository;

import API_Practice.com.example.demo.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface UserRepositry extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    Page<User> findByFullnameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String fullname, String email, Pageable pageable
    );
}
