package pl.tul.emailsemotions.statsservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.statsservice.Model.FormalityStatistics;
import pl.tul.emailsemotions.statsservice.clients.FormalityClient;
import pl.tul.emailsemotions.statsservice.clients.model.Emotionable;
import pl.tul.emailsemotions.statsservice.clients.model.Formalitable;
import pl.tul.emailsemotions.statsservice.clients.model.FormalityEvaluation;
import pl.tul.emailsemotions.statsservice.clients.model.FormalityResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class FormalityStatisticsService {
    private final FormalityClient formalityClient;

    public FormalityStatistics calculateFormalityStatistics(Long userId) {
        log.info("Calculating formality statistics for user "+userId);
        List<FormalityResult> formalityResults = formalityClient.getAllFormalityResults(userId);
        List<FormalityEvaluation> formalityEvaluations = formalityClient.getAllFormalityEvaluations(userId);

        Formalitable[] formalityResultsArray = formalityResults.toArray(new Formalitable[0]);
        Formalitable[] formalityEvaluationsArray = formalityEvaluations.toArray(new Formalitable[0]);
        HashMap<String, Double> recognitionStats = calculateStatistics(formalityResultsArray);
        HashMap<String, Double> evaluationStats = calculateStatistics(formalityEvaluationsArray);

        return FormalityStatistics.builder()
            .numberOfRecognitions(formalityResults.size())
            .numberOfEvaluations(formalityEvaluations.size())

            .averageRecognitionFormality(recognitionStats.get("formality"))
            .averageRecognitionInformality(recognitionStats.get("informality"))

            .averageEvaluationFormality(evaluationStats.get("formality"))
            .averageEvaluationInformality(evaluationStats.get("informality"))
            .build();
    }

    private HashMap<String, Double> calculateStatistics(Formalitable[] formalitableItems) {
        List<Formalitable> formalitableList = Arrays.asList(formalitableItems);
        HashMap<String, Double> averages = new HashMap<>();
        averages.put("formality", 0.0);
        averages.put("informality", 0.0);

        for (Formalitable item : formalitableList) {
            averages.put("formality", averages.get("formality") + item.getFormality().doubleValue());
            averages.put("informality", averages.get("informality") + item.getInformality().doubleValue());
        }

        for (Map.Entry<String, Double> average: averages.entrySet()) {
            average.setValue(average.getValue() / formalitableList.size());
        }

        return averages;
    }
}
