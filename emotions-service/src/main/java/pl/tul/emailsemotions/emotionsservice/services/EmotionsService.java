package pl.tul.emailsemotions.emotionsservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.emotionsservice.clients.AIClient;
import pl.tul.emailsemotions.emotionsservice.clients.models.AIResult;
import pl.tul.emailsemotions.emotionsservice.model.EmotionsResult;
import pl.tul.emailsemotions.emotionsservice.model.Text;
import pl.tul.emailsemotions.emotionsservice.repositories.EmotionsResultRepository;

import java.util.LinkedList;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class EmotionsService {
    private final EmotionsResultRepository emotionsResultRepository;
    private final TextService textService;
    private final AIClient aiClient;
    public EmotionsResult recognizeFormality(Text text) {
        log.info("Recognizing emotions...");
        textService.add(text);
        AIResult aiResult = countEmotions(text);
        EmotionsResult result = EmotionsResult
            .builder()
            .textId(text.getId())
            .angry(aiResult.getAngry())
            .fear(aiResult.getFear())
            .happy(aiResult.getHappy())
            .sad(aiResult.getSad())
            .surprise(aiResult.getSurprise())
            .build();
        text.addEmotionsResult(result);
        emotionsResultRepository.save(result);
        return result;
    }

    public List<EmotionsResult> getAllByTextId(Long textId) {
        log.info("Looking for emotions results for text with id "+textId);
        return emotionsResultRepository.findAllByTextId(textId);
    }

    public List<EmotionsResult> getAllByUserId(Long userId) {
        log.info("Looking for emotions results for user with id "+userId);
        List<Text> texts = textService.getAllByUserId(userId);
        List<EmotionsResult> emotionsResults = new LinkedList<>();
        for (Text text : texts) {
            emotionsResults.addAll(getAllByTextId(text.getId()));
        }
        return emotionsResults;
    }
    public AIResult countEmotions(Text text) {
        AIResult aiResult = aiClient.countEmotions(text.getText());
        return aiResult;
    }
}
