package pl.tul.emailsemotions.formalityservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.emailsemotions.formalityservice.model.Text;

public interface TextRepository extends JpaRepository<Text, Long> {
}

