package pl.tul.emailsemotions.statsservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.statsservice.Model.EmotionsStatistics;
import pl.tul.emailsemotions.statsservice.Model.FormalityStatistics;
import pl.tul.emailsemotions.statsservice.clients.EmotionsClient;
import pl.tul.emailsemotions.statsservice.clients.FormalityClient;
import pl.tul.emailsemotions.statsservice.clients.model.*;
import reactor.util.function.Tuple2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class EmotionsStatisticsService {
    private final EmotionsClient emotionsClient;

    public EmotionsStatistics calculateEmotionsStatistics(Long userId) {
        List<EmotionsResult> emotionsResults = emotionsClient.getAllEmotionsResults(userId);
        List<EmotionsEvaluation> emotionsEvaluations = emotionsClient.getAllEmotionsEvaluations(userId);

        Emotionable[] emotionsResultsArray = emotionsResults.toArray(new Emotionable[0]);
        Emotionable[] emotionsEvaluationsArray = emotionsEvaluations.toArray(new Emotionable[0]);
        HashMap<String, Double> recognitionStats = calculateStatistics(emotionsResultsArray);
        HashMap<String, Double> evaluationStats = calculateStatistics(emotionsEvaluationsArray);

        return EmotionsStatistics.builder()
            .numberOfRecognitions(emotionsResults.size())
            .numberOfEvaluations(emotionsEvaluations.size())

            .averageRecognitionAngry(recognitionStats.get("angry"))
            .averageRecognitionFear(recognitionStats.get("fear"))
            .averageRecognitionHappy(recognitionStats.get("happy"))
            .averageRecognitionSad(recognitionStats.get("sad"))
            .averageRecognitionSurprise(recognitionStats.get("surprise"))

            .averageEvaluationAngry(evaluationStats.get("angry"))
            .averageEvaluationFear(evaluationStats.get("fear"))
            .averageEvaluationHappy(evaluationStats.get("happy"))
            .averageEvaluationSad(evaluationStats.get("sad"))
            .averageEvaluationSurprise(evaluationStats.get("surprise"))
            .build();
    }

    private HashMap<String, Double> calculateStatistics(Emotionable[] emotionableItems) {
        List<Emotionable> emotionableList = Arrays.asList(emotionableItems);
        HashMap<String, Double> averages = new HashMap<>();
        averages.put("angry", 0.0);
        averages.put("fear", 0.0);
        averages.put("happy", 0.0);
        averages.put("sad", 0.0);
        averages.put("surprise", 0.0);

        for (Emotionable item : emotionableList) {
            averages.put("angry", averages.get("angry") + item.getAngry().doubleValue());
            averages.put("fear", averages.get("fear") + item.getFear().doubleValue());
            averages.put("happy", averages.get("happy") + item.getHappy().doubleValue());
            averages.put("sad", averages.get("sad") + item.getSad().doubleValue());
            averages.put("surprise", averages.get("surprise") + item.getSurprise().doubleValue());
        }

        for (Map.Entry<String, Double> average: averages.entrySet()) {
            average.setValue(average.getValue() / emotionableList.size());
        }

        return averages;
    }
}
