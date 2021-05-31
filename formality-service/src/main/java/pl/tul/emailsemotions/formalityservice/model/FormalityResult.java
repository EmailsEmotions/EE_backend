package pl.tul.emailsemotions.formalityservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(toBuilder = true)
@Setter
@Getter
@Table(name = "formality")
public class FormalityResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "formality")
    @NotEmpty
    private Double formality;

    @Column(name = "informality")
    @NotEmpty
    private Double informality;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_id", referencedColumnName = "id")
    private Text text;
}
