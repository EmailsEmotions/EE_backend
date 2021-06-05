package pl.tul.emailsemotions.statsservice.clients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormalityEvaluation implements Formalitable {
    private Integer formality;
    private Integer informality;
}

