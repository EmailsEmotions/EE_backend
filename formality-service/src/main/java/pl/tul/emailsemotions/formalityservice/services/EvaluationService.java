package pl.tul.emailsemotions.formalityservice.services;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.formalityservice.model.FormalityEvaluation;
import pl.tul.emailsemotions.formalityservice.model.FormalityResult;
import pl.tul.emailsemotions.formalityservice.model.Text;
import pl.tul.emailsemotions.formalityservice.repositories.FormalityEvaluationRepository;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EvaluationService {
    private final FormalityEvaluationRepository formalityEvaluationRepository;
    private final TextService textService;

    public FormalityEvaluation add(FormalityEvaluation formalityEvaluation) throws NotFoundException {
        log.info("Adding formality evaluation");
        formalityEvaluationRepository.save(formalityEvaluation);
        return formalityEvaluation;
    }

    public List<FormalityEvaluation> getAllByTextId(Long textId) {
        log.info("Looking for formality evaluation with id "+textId);
        return formalityEvaluationRepository.findAllByTextId(textId);
    }

    public List<FormalityEvaluation> getAllByUserId(Long userId) {
        log.info("Looking for formality evaluations for user with id "+userId);
        List<Text> texts = textService.getAllByUserId(userId);
        List<FormalityEvaluation> formalityEvaluations = new LinkedList<>();
        for (Text text : texts) {
            formalityEvaluations.addAll(getAllByTextId(text.getId()));
        }
        return formalityEvaluations;
    }
}
