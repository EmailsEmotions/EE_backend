package pl.tul.emailsemotions.userservice.clientModels.emotions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tul.emailsemotions.userservice.clientModels.BaseText;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmotionsText extends BaseText {
    private List<EmotionsResult> emotionsResults = new LinkedList<>();
}
