package pl.tul.emailsemotions.apigateway.auth.rest.response;

import lombok.Builder;
import lombok.Data;
import pl.tul.emailsemotions.api.User;

@Builder
@Data
public class LoginResponse {
    private String token;
    private User user;
}
