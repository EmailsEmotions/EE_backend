package pl.tul.emailsemotions.userservice.clients.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tul.emailsemotions.userservice.utils.Utilities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailObject {
    @Pattern(regexp = Utilities.EMAIL_REGEX)
    @NotNull
    @Size(min = 1, message = "Please, set an email address to send the message to it")
    private String recipient;

    @NotNull
    @Size(min = 1, message = "Please, set a subject for mail")
    private String subject;
    private HashMap<String,String> templateMap;
}

