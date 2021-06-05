package pl.tul.emailsemotions.statsservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.tul.emailsemotions.statsservice.clients.model.EmotionsEvaluation;
import pl.tul.emailsemotions.statsservice.clients.model.EmotionsResult;
import pl.tul.emailsemotions.statsservice.clients.model.FormalityEvaluation;
import pl.tul.emailsemotions.statsservice.clients.model.FormalityResult;

import java.util.List;

@Component
@FeignClient(value = "http://emotions-service")
public interface EmotionsClient {

    @RequestMapping(value="/recognitions/{userId}", method = RequestMethod.GET)
    List<EmotionsResult> getAllEmotionsResults(@PathVariable("userId") Long userId);

    @RequestMapping(value="/evaluations/{userId}", method = RequestMethod.GET)
    List<EmotionsEvaluation> getAllEmotionsEvaluations(@PathVariable("userId") Long userId);
}
