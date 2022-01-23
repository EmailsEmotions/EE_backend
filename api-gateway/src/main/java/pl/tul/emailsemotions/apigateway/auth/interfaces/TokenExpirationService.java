package pl.tul.emailsemotions.apigateway.auth.interfaces;

import java.time.LocalDateTime;

public interface TokenExpirationService {

    LocalDateTime getTokenExpirationTime(String token);

}
