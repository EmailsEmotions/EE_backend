package pl.tul.emailsemotions.formalityservice.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Setter
@Getter
@Entity(name = "FormalityResult")
@Table(name = "formality_result")
public class FormalityResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "formality")
    private Double formality;

    @Column(name = "informality")
    private Double informality;
}
