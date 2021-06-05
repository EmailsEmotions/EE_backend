package pl.tul.emailsemotions.statsservice.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormalityStatistics {
    private Integer numberOfRecognitions;
    private Integer numberOfEvaluations;

    private Double averageRecognitionFormality;
    private Double averageRecognitionInformality;

    private Double averageEvaluationFormality;
    private Double averageEvaluationInformality;
}
