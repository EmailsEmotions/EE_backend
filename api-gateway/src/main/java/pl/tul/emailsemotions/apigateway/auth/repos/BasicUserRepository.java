package pl.tul.emailsemotions.apigateway.auth.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.emailsemotions.apigateway.auth.models.UserData;

import java.util.Optional;

public interface BasicUserRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUsername(String username);
}
