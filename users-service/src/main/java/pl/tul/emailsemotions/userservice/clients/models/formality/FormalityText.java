package pl.tul.emailsemotions.userservice.clients.models.formality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tul.emailsemotions.userservice.clients.models.BaseText;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormalityText extends BaseText {
    private List<FormalityResult> formalityResults = new LinkedList<>();
}
