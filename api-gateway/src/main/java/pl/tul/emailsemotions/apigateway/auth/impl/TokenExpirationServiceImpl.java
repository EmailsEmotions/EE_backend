package pl.tul.emailsemotions.apigateway.auth.impl;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.apigateway.auth.interfaces.TokenExpirationService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service("tokenExpirationService")
public class TokenExpirationServiceImpl implements TokenExpirationService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public LocalDateTime getTokenExpirationTime(String token) {
        Date date = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration();
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
