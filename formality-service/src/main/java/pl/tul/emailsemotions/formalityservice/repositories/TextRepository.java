package pl.tul.emailsemotions.formalityservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.emailsemotions.formalityservice.model.Text;

import java.util.List;

public interface TextRepository extends JpaRepository<Text, Long> {
    List<Text> findAllByUserId(Long id);
}

