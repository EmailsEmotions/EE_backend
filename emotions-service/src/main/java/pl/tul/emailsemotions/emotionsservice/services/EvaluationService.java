package pl.tul.emailsemotions.emotionsservice.services;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.emotionsservice.model.EmotionsEvaluation;
import pl.tul.emailsemotions.emotionsservice.model.Text;
import pl.tul.emailsemotions.emotionsservice.repositories.EmotionsEvaluationRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EvaluationService {
    private final EmotionsEvaluationRepository emotionsEvaluationRepository;

    public EmotionsEvaluation add(EmotionsEvaluation emotionsEvaluation) throws NotFoundException {
        emotionsEvaluationRepository.save(emotionsEvaluation);
        return emotionsEvaluation;
    }

    public List<EmotionsEvaluation> getAllByTextId(Long textId) {
        return emotionsEvaluationRepository.findAllByTextId(textId);
    }
}
