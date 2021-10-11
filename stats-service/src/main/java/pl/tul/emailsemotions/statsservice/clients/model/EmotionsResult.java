package pl.tul.emailsemotions.statsservice.clients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmotionsResult implements Emotionable {
    private Double angry;
    private Double fear;
    private Double happy;
    private Double sad;
    private Double surprise;
}
