package pl.tul.emailsemotions.formalityservice.services;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.formalityservice.model.FormalityEvaluation;
import pl.tul.emailsemotions.formalityservice.repositories.FormalityEvaluationRepository;
import pl.tul.emailsemotions.formalityservice.model.Text;

@Service
@AllArgsConstructor
@Slf4j
public class EvaluationService {
    private final FormalityEvaluationRepository formalityEvaluationRepository;
    private final TextService textService;

    public FormalityEvaluation add(FormalityEvaluation formalityEvaluation) throws NotFoundException {
        formalityEvaluationRepository.save(formalityEvaluation);
        Text text = textService.get(formalityEvaluation.getTextId());
        text.addFormalityEvaluation(formalityEvaluation);
        return formalityEvaluation;
    }
}
