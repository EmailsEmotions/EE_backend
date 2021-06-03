package pl.tul.emailsemotions.emotionsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "texts")
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "text")
    @NotEmpty
    private String text;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "text_id")
    private List<EmotionsResult> emotionsResults = new LinkedList<>();

    public void addEmotionsResult(EmotionsResult emotionsResult) {
        emotionsResults.add(emotionsResult);
    }
}
