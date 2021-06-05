package pl.tul.emailsemotions.statsservice.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmotionsStatistics {
    private Integer numberOfRecognitions;
    private Integer numberOfEvaluations;

    private Double averageRecognitionAngry;
    private Double averageRecognitionFear;
    private Double averageRecognitionHappy;
    private Double averageRecognitionSad;
    private Double averageRecognitionSurprise;

    private Double averageEvaluationAngry;
    private Double averageEvaluationFear;
    private Double averageEvaluationHappy;
    private Double averageEvaluationSad;
    private Double averageEvaluationSurprise;
}
