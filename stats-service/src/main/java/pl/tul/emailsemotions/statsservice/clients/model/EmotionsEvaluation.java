package pl.tul.emailsemotions.statsservice.clients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmotionsEvaluation implements Emotionable {
    private Integer angry;
    private Integer fear;
    private Integer happy;
    private Integer sad;
    private Integer surprise;
}
