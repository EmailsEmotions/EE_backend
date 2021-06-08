package pl.tul.emailsemotions.userservice.clients.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseText {
    private Long id;
    private String text;
}
