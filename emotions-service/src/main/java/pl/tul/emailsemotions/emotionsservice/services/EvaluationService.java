package pl.tul.emailsemotions.emotionsservice.services;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.emotionsservice.model.EmotionsEvaluation;
import pl.tul.emailsemotions.emotionsservice.model.Text;
import pl.tul.emailsemotions.emotionsservice.repositories.EmotionsEvaluationRepository;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EvaluationService {
    private final EmotionsEvaluationRepository emotionsEvaluationRepository;
    private final TextService textService;

    public EmotionsEvaluation add(EmotionsEvaluation emotionsEvaluation) throws NotFoundException {
        log.info("Adding emotions evaluation");
        emotionsEvaluationRepository.save(emotionsEvaluation);
        return emotionsEvaluation;
    }

    public List<EmotionsEvaluation> getAllByTextId(Long textId) {
        log.info("Looking for emotions evaluation with id "+textId);
        return emotionsEvaluationRepository.findAllByTextId(textId);
    }

    public List<EmotionsEvaluation> getAllByUserId(Long userId) {
        log.info("Looking for emotions evaluations for user with id "+userId);
        List<Text> texts = textService.getAllByUserId(userId);
        List<EmotionsEvaluation> emotionsEvaluations = new LinkedList<>();
        for (Text text : texts) {
            emotionsEvaluations.addAll(getAllByTextId(text.getId()));
        }
        return emotionsEvaluations;
    }
}
