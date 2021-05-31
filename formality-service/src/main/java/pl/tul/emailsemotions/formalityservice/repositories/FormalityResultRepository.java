package pl.tul.emailsemotions.formalityservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.emailsemotions.formalityservice.model.FormalityResult;

public interface FormalityResultRepository extends JpaRepository<FormalityResult, Integer> {
}

