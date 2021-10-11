package pl.tul.emailsemotions.emotionsservice.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Setter
@Getter
@Entity
@Table(name = "emotions_evaluation")
public class EmotionsEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text_id")
    private Long textId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "angry")
    private Integer angry;

    @Column(name = "fear")
    private Integer fear;

    @Column(name = "happy")
    private Integer happy;

    @Column(name = "sad")
    private Integer sad;

    @Column(name = "surprise")
    private Integer surprise;
}
