package pl.tul.emailsemotions.emotionsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.emailsemotions.emotionsservice.model.EmotionsEvaluation;

import java.util.List;

public interface EmotionsEvaluationRepository extends JpaRepository<EmotionsEvaluation, Long> {
    List<EmotionsEvaluation> findAllByTextId(Long id);
}
