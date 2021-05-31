package pl.tul.emailsemotions.formalityservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.formalityservice.model.FormalityResult;
import pl.tul.emailsemotions.formalityservice.model.Text;

@Service
@AllArgsConstructor
@Slf4j
public class FormalityService {
    public FormalityResult recognizeFormality(Text text) {
        return FormalityResult.builder().formality(0.2).informality(0.8).text(text).build();
    }
}
