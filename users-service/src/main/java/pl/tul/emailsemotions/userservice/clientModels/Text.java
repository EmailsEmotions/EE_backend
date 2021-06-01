package pl.tul.emailsemotions.userservice.clientModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Text {
    private Long id;
    private String text;
    private List<FormalityResult> formalityResults = new LinkedList<>();
}
