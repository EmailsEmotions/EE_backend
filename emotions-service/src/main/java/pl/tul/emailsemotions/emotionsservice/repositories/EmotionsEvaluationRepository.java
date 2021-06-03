package pl.tul.emailsemotions.emotionsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.emailsemotions.emotionsservice.model.EmotionsEvaluation;

public interface EmotionsEvaluationRepository extends JpaRepository<EmotionsEvaluation, Long> {
}
