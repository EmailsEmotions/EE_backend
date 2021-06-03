package pl.tul.emailsemotions.formalityservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.emailsemotions.formalityservice.model.FormalityEvaluation;

public interface FormalityEvaluationRepository extends JpaRepository<FormalityEvaluation, Long> {
}
