package pl.tul.emailsemotions.formalityservice.services;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.formalityservice.model.FormalityEvaluation;
import pl.tul.emailsemotions.formalityservice.repositories.FormalityEvaluationRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EvaluationService {
    private final FormalityEvaluationRepository formalityEvaluationRepository;

    public FormalityEvaluation add(FormalityEvaluation formalityEvaluation) throws NotFoundException {
        formalityEvaluationRepository.save(formalityEvaluation);
        return formalityEvaluation;
    }

    public List<FormalityEvaluation> getAllByTextId(Long textId) {
        return formalityEvaluationRepository.findAllByTextId(textId);
    }
}
