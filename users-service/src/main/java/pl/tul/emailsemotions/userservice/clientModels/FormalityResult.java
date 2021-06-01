package pl.tul.emailsemotions.userservice.clientModels;

import lombok.*;

import javax.persistence.*;

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
