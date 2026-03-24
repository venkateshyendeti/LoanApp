package API_Practice.com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_management")
public class UserManegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "account_status",nullable = false)
    private String accountStatus;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "mobile_number", unique = true)
    private String mobileNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    private Boolean enabled;

}
