package API_Practice.com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String fullname;

    @Column(unique = true)
    private String email;

    private LocalDate dateOfBirth;

    private  String gender;

    private String password;

    private LocalDateTime createdBy;


}
