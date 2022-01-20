package pl.tul.emailsemotions.apigateway.auth.impl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.apigateway.auth.interfaces.TokenExpirationService;
import pl.tul.emailsemotions.shared.exceptions.UnauthorizedActionException;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service("tokenExpirationService")
public class TokenExpirationServiceImpl implements TokenExpirationService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    @Override
    public LocalDateTime getTokenExpirationTime(String token) {
        try {
            Date date = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration();
            return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedActionException("Token expired");
        } catch (SignatureException e) {
            throw new UnauthorizedActionException("Bad token");
        }
    }
}
