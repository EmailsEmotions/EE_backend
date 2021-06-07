package pl.tul.emailsemotions.userservice.clients.models.formality;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Setter
@Getter
public class FormalityResult {
    private Long id;

    private Double formality;

    private Double informality;
}
