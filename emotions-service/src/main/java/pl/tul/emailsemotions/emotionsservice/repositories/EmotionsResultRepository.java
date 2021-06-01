package pl.tul.emailsemotions.emotionsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.emailsemotions.emotionsservice.model.EmotionsResult;

public interface EmotionsResultRepository extends JpaRepository<EmotionsResult, Long> {
}

