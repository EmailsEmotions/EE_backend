package pl.tul.emailsemotions.formalityservice.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Setter
@Getter
@Entity
@Table(name = "formality_evaluation")
public class FormalityEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text_id")
    private Long textId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "formality")
    private Integer formality;

    @Column(name = "informality")
    private Integer informality;
}
