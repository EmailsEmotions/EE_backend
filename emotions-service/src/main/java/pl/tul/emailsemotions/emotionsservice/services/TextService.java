package pl.tul.emailsemotions.emotionsservice.services;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.emotionsservice.model.Text;
import pl.tul.emailsemotions.emotionsservice.repositories.TextRepository;

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
}
