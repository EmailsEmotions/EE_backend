package pl.tul.emailsemotions.emotionsservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.emotionsservice.model.EmotionsResult;
import pl.tul.emailsemotions.emotionsservice.model.Text;
import pl.tul.emailsemotions.emotionsservice.repositories.EmotionsResultRepository;
import pl.tul.emailsemotions.emotionsservice.repositories.TextRepository;


@Service
@AllArgsConstructor
@Slf4j
public class EmotionsService {
    private final EmotionsResultRepository emotionsResultRepository;
    private final TextRepository textRepository;

    public EmotionsResult recognizeFormality(Text text) {
        EmotionsResult result = EmotionsResult
            .builder()
            .angry(0.2)
            .fear(0.3)
            .happy(0.2)
            .sad(0.8)
            .surprise(0.3)
            .build();
        text.addEmotionsResult(result);
        textRepository.save(text);
        emotionsResultRepository.save(result);
        return result;
    }
}
