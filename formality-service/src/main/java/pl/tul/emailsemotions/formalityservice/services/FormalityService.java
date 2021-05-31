package pl.tul.emailsemotions.formalityservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.formalityservice.model.FormalityResult;
import pl.tul.emailsemotions.formalityservice.model.Text;
import pl.tul.emailsemotions.formalityservice.repositories.FormalityResultRepository;
import pl.tul.emailsemotions.formalityservice.repositories.TextRepository;

@Service
@AllArgsConstructor
@Slf4j
public class FormalityService {
    private final FormalityResultRepository formalityResultRepository;
    private final TextRepository textRepository;

    public FormalityResult recognizeFormality(Text text) {
        FormalityResult result = FormalityResult
            .builder()
            .formality(0.2)
            .informality(0.8)
            .build();
        text.addFormalityResult(result);
        textRepository.save(text);
        formalityResultRepository.save(result);
        return result;
    }
}
