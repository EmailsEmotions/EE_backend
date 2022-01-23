package pl.tul.emailsemotions.apigateway.auth.rest.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BlackListedToken {
    private int id;
    private String token;
    private LocalDateTime expirationTime;
}
