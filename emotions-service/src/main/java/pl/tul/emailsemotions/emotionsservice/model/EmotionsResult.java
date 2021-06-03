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
@Table(name = "emotions_result")
public class EmotionsResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text_id")
    private Long textId;

    @Column(name = "angry")
    private Double angry;

    @Column(name = "fear")
    private Double fear;

    @Column(name = "happy")
    private Double happy;

    @Column(name = "sad")
    private Double sad;

    @Column(name = "surprise")
    private Double surprise;
}
