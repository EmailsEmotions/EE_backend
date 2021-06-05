package pl.tul.emailsemotions.statsservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.statsservice.Model.FormalityStatistics;
import pl.tul.emailsemotions.statsservice.clients.FormalityClient;
import pl.tul.emailsemotions.statsservice.clients.model.FormalityEvaluation;
import pl.tul.emailsemotions.statsservice.clients.model.FormalityResult;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FormalityStatisticsService {
    private final FormalityClient formalityClient;

    public FormalityStatistics calculateFormalityStatistics(Long userId) {
        List<FormalityResult> formalityResults = formalityClient.getAllFormalityResults(userId);
        List<FormalityEvaluation> formalityEvaluations = formalityClient.getAllFormalityEvaluations(userId);

        Double averageRecognitionFormality = 0.0;
        Double averageRecognitionInformality = 0.0;
        for (FormalityResult formalityResult : formalityResults) {
            averageRecognitionFormality += formalityResult.getFormality();
            averageRecognitionInformality += formalityResult.getInformality();
        }
        averageRecognitionFormality /= formalityResults.size();
        averageRecognitionInformality /= formalityResults.size();

        Double averageEvaluationFormality = 0.0;
        Double averageEvaluationInformality = 0.0;
        for (FormalityEvaluation formalityEvaluation : formalityEvaluations) {
            averageEvaluationFormality += formalityEvaluation.getFormality();
            averageEvaluationInformality += formalityEvaluation.getInformality();
        }
        averageEvaluationFormality /= formalityEvaluations.size();
        averageEvaluationInformality /= formalityEvaluations.size();

        return FormalityStatistics.builder()
            .numberOfRecognitions(formalityResults.size())
            .numberOfEvaluations(formalityEvaluations.size())
            .averageRecognitionFormality(averageRecognitionFormality)
            .averageRecognitionInformality(averageRecognitionInformality)
            .averageEvaluationFormality(averageEvaluationFormality)
            .averageEvaluationInformality(averageEvaluationInformality)
            .build();
    }
}
