package pl.tul.emailsemotions.formalityservice.services;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.formalityservice.model.FormalityEvaluation;
import pl.tul.emailsemotions.formalityservice.model.FormalityResult;
import pl.tul.emailsemotions.formalityservice.model.Text;
import pl.tul.emailsemotions.formalityservice.repositories.TextRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TextService {
    private final TextRepository textRepository;

    public Text get(Long id) throws NotFoundException {
        if (textRepository.findById(id).isPresent()) {
            return textRepository.findById(id).get();
        }
        log.error("Text with id: " + id + " not found");
        throw new NotFoundException("Text not found");
    }

    public List<Text> getAll() {
        return textRepository.findAll();
    }

    public Text add(Text text) {
        return textRepository.save(text);
    }

    public List<Text> getAllByUserId(Long userId) {
        return textRepository.findAllByUserId(userId);
    }
}
