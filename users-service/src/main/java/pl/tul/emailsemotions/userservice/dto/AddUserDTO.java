package pl.tul.emailsemotions.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pl.tul.emailsemotions.userservice.utils.Utilities;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@Builder
public class AddUserDTO {
    private String username;
    @Pattern(regexp = Utilities.EMAIL_REGEX)
    private String email;
    private String password;
}
