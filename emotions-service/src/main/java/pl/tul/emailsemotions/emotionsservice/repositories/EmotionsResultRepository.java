package pl.tul.emailsemotions.emotionsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.emailsemotions.emotionsservice.model.EmotionsResult;

import java.util.List;

public interface EmotionsResultRepository extends JpaRepository<EmotionsResult, Long> {
    List<EmotionsResult> findAllByTextId(Long id);
}
