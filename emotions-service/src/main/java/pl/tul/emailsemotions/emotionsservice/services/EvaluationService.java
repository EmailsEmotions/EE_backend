package pl.tul.emailsemotions.emotionsservice.services;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.emotionsservice.model.EmotionsEvaluation;
import pl.tul.emailsemotions.emotionsservice.model.Text;
import pl.tul.emailsemotions.emotionsservice.repositories.EmotionsEvaluationRepository;

@Service
@AllArgsConstructor
@Slf4j
public class EvaluationService {
    private final EmotionsEvaluationRepository emotionsEvaluationRepository;
    private final TextService textService;

    public EmotionsEvaluation add(EmotionsEvaluation emotionsEvaluation) throws NotFoundException {
        emotionsEvaluationRepository.save(emotionsEvaluation);
        Text text = textService.get(emotionsEvaluation.getTextId());
        text.addEmotionsEvaluation(emotionsEvaluation);
        return emotionsEvaluation;
    }
}
