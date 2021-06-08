package pl.tul.emailsemotions.emotionsservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.emotionsservice.model.EmotionsEvaluation;
import pl.tul.emailsemotions.emotionsservice.model.EmotionsResult;
import pl.tul.emailsemotions.emotionsservice.model.Text;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HistoryService {
    private final TextService textService;
    private final EvaluationService evaluationService;
    private final EmotionsService emotionsService;

    public List<Text> getUserHistory(Long userId) {
        log.info("Getting history for userId "+userId);
        List<Text> texts = textService.getAllByUserId(userId);
        for (Text text: texts) {
            List<EmotionsResult> formalityResults =
                emotionsService.getAllByTextId(text.getId());
            List<EmotionsEvaluation> formalityEvaluations =
                evaluationService.getAllByTextId(text.getId());

            text.setEmotionsResults(formalityResults);
            for (EmotionsEvaluation formalityEvaluation: formalityEvaluations) {
                if (formalityEvaluation.getUserId().equals(userId)) {
                    text.addEmotionsEvaluation(formalityEvaluation);
                }
            }
        }
        return texts;
    }
}
