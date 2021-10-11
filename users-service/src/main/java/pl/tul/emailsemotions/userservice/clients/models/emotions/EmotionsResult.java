package pl.tul.emailsemotions.userservice.clients.models.emotions;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Setter
@Getter
public class EmotionsResult {
    private Long id;

    private Double angry;
    private Double fear;
    private Double happy;
    private Double sad;
    private Double surprise;
}
