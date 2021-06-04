package pl.tul.emailsemotions.formalityservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.formalityservice.model.FormalityEvaluation;
import pl.tul.emailsemotions.formalityservice.model.FormalityResult;
import pl.tul.emailsemotions.formalityservice.model.Text;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HistoryService {
    private final TextService textService;
    private final EvaluationService evaluationService;
    private final FormalityService formalityService;

    public List<Text> getUserHistory(Long userId) {

        List<Text> texts = textService.getAllByUserId(userId);
        for (Text text: texts) {
            List<FormalityResult> formalityResults =
                formalityService.getAllByTextId(text.getId());
            List<FormalityEvaluation> formalityEvaluations =
                evaluationService.getAllByTextId(text.getId());

            text.setFormalityResults(formalityResults);
            for (FormalityEvaluation formalityEvaluation: formalityEvaluations) {
                if (formalityEvaluation.getUserId().equals(userId)) {
                    text.addFormalityEvaluation(formalityEvaluation);
                }
            }
        }
        return texts;
    }
}
