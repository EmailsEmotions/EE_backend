package pl.tul.emailsemotions.emotionsservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public EmotionsResult recognizeFormality(Text text) {
        textService.add(text);
        EmotionsResult result = EmotionsResult
            .builder()
            .textId(text.getId())
            .angry(0.2)
            .fear(0.3)
            .happy(0.2)
            .sad(0.8)
            .surprise(0.3)
            .build();
        text.addEmotionsResult(result);
        emotionsResultRepository.save(result);
        return result;
    }

    public List<EmotionsResult> getAllByTextId(Long textId) {
        return emotionsResultRepository.findAllByTextId(textId);
    }

    public List<EmotionsResult> getAllByUserId(Long userId) {
        List<Text> texts = textService.getAllByUserId(userId);
        List<EmotionsResult> emotionsResults = new LinkedList<>();
        for (Text text : texts) {
            emotionsResults.addAll(getAllByTextId(text.getId()));
        }
        return emotionsResults;
    }
}
