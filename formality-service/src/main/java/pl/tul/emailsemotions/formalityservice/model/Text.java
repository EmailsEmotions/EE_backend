package pl.tul.emailsemotions.formalityservice.model;

import lombok.*;

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

    @Column(name = "text")
    @NotEmpty
    private String text;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "text_id")
    private List<FormalityResult> formalityResults = new LinkedList<>();

    public void addFormalityResult(FormalityResult formalityResult) {
        formalityResults.add(formalityResult);
    }
}
