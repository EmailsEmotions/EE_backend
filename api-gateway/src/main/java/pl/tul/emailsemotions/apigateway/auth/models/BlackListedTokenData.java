package pl.tul.emailsemotions.apigateway.auth.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tul.emailsemotions.apigateway.auth.rest.response.BlackListedToken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "black_listed_tokens")
public class BlackListedTokenData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "expiration_time", nullable = false)
    private LocalDateTime expirationTime;

    public BlackListedTokenData(BlackListedToken blackListedToken) {
        this.id = blackListedToken.getId();
        this.token = blackListedToken.getToken();
        this.expirationTime = blackListedToken.getExpirationTime();
    }

    public BlackListedToken toBlackListedToken() {
        return BlackListedToken.builder()
                .id(getId())
                .token(getToken())
                .expirationTime(getExpirationTime())
                .build();
    }
}
