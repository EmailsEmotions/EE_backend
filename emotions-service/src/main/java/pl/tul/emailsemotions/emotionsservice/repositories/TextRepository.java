package pl.tul.emailsemotions.emotionsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tul.emailsemotions.emotionsservice.model.Text;

import java.util.List;

public interface TextRepository extends JpaRepository<Text, Long> {
    List<Text> findAllByUserId(Long id);
}

