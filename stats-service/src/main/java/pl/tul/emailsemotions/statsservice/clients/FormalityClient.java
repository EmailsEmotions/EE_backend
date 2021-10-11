package pl.tul.emailsemotions.statsservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.tul.emailsemotions.statsservice.clients.model.FormalityEvaluation;
import pl.tul.emailsemotions.statsservice.clients.model.FormalityResult;

import java.util.List;

@Component
@FeignClient(value = "http://formality-service")
public interface FormalityClient {

    @RequestMapping(value="/recognitions/{userId}", method = RequestMethod.GET)
    List<FormalityResult> getAllFormalityResults(@PathVariable("userId") Long userId);

    @RequestMapping(value="/evaluations/{userId}", method = RequestMethod.GET)
    List<FormalityEvaluation> getAllFormalityEvaluations(@PathVariable("userId") Long userId);
}
