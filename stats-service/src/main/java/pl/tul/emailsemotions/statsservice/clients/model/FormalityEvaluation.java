package pl.tul.emailsemotions.statsservice.clients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormalityEvaluation {
    private Double formality;
    private Double informality;
}

