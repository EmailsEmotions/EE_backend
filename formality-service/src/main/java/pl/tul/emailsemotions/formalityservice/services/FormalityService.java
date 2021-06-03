package pl.tul.emailsemotions.formalityservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.formalityservice.model.FormalityResult;
import pl.tul.emailsemotions.formalityservice.model.Text;
import pl.tul.emailsemotions.formalityservice.repositories.FormalityResultRepository;

@Service
@AllArgsConstructor
@Slf4j
public class FormalityService {
    private final FormalityResultRepository formalityResultRepository;
    private final TextService textService;

    public FormalityResult recognizeFormality(Text text) {
        FormalityResult result = FormalityResult
            .builder()
            .formality(0.2)
            .informality(0.8)
            .build();
        text.addFormalityResult(result);
        textService.add(text);
        formalityResultRepository.save(result);
        return result;
    }
}
