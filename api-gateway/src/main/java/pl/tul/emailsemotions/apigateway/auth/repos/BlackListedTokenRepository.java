package pl.tul.emailsemotions.apigateway.auth.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.emailsemotions.apigateway.auth.models.BlackListedTokenData;

public interface BlackListedTokenRepository extends JpaRepository<BlackListedTokenData, Integer> {
}
