package pl.tul.emailsemotions.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pl.tul.emailsemotions.userservice.utils.Utilities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@Builder
public class AddUserDTO {
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Pattern(regexp = Utilities.EMAIL_REGEX)
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = Utilities.PASSWORD_REGEX)
    private String password;
}
