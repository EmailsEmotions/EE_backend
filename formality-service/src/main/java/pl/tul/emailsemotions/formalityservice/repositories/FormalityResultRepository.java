package pl.tul.emailsemotions.formalityservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.emailsemotions.formalityservice.model.FormalityResult;

import java.util.List;

public interface FormalityResultRepository extends JpaRepository<FormalityResult, Long> {
    List<FormalityResult> findAllByTextId(Long id);
}

