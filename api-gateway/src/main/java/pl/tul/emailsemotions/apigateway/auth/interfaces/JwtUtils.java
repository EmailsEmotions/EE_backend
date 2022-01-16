package pl.tul.emailsemotions.apigateway.auth.interfaces;

import org.springframework.security.core.Authentication;

public interface JwtUtils {

    String generateJwtToken(Authentication authentication);

    String getUsernameFromJwtToken(String token);

    boolean isJwtTokenValid(String token);
}
