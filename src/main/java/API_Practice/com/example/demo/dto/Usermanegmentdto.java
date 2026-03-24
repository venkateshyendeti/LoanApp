package API_Practice.com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Usermanegmentdto {

    @NotBlank(message = "Account type is required")
    private String accountType;

    @NotBlank(message = "Account status is required")
    private String accountStatus;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;

    @Size(max = 30, message = "Last name must be less than 30 characters")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Mobile number must be valid 10 digits"
    )
    private String mobile;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String  confirmPassword;

    @NotNull(message = "Enabled status is required")
    private Boolean enabled;

}
