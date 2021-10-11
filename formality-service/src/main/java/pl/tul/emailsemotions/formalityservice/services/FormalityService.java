package pl.tul.emailsemotions.formalityservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.formalityservice.clients.AIClient;
import pl.tul.emailsemotions.formalityservice.clients.model.AIResult;
import pl.tul.emailsemotions.formalityservice.model.FormalityEvaluation;
import pl.tul.emailsemotions.formalityservice.model.FormalityResult;
import pl.tul.emailsemotions.formalityservice.model.Text;
import pl.tul.emailsemotions.formalityservice.repositories.FormalityResultRepository;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FormalityService {
    private final FormalityResultRepository formalityResultRepository;
    private final TextService textService;
    private final AIClient aiClient;

    public FormalityResult recognizeFormality(Text text) {
        log.info("Recognizing formality...");
        textService.add(text);
        AIResult aiResult = getAiResult(text);
        FormalityResult result = FormalityResult
            .builder()
            .textId(text.getId())
            .formality(aiResult.getFormal())
            .informality(aiResult.getInformal())
            .build();
        text.addFormalityResult(result);
        formalityResultRepository.save(result);
        return result;
    }

    public List<FormalityResult> getAllByTextId(Long textId) {
        log.info("Looking for formality results for text with id "+textId);
        return formalityResultRepository.findAllByTextId(textId);
    }

    public List<FormalityResult> getAllByUserId(Long userId) {
        log.info("Looking for formality results for user with id "+userId);
        List<Text> texts = textService.getAllByUserId(userId);
        List<FormalityResult> formalityResults = new LinkedList<>();
        for (Text text : texts) {
            formalityResults.addAll(getAllByTextId(text.getId()));
        }
        return formalityResults;
    }
    public AIResult getAiResult(Text text) {
        AIResult airesult = aiClient.countFormal(text.getText());
        return airesult;
    }
}
