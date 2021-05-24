package pl.tul.emailsemotions.formalityservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.formalityservice.model.Text;

@RestController
@AllArgsConstructor
public class FormalityController {

    @PostMapping("/countnos")
    @ResponseBody
    @CrossOrigin(value = "*")
    public ResponseEntity makeTest(@RequestBody Text text) {
        String[] words = text.getText().split(" ");
        String pattern = "no";

        int count = 0;
        for (String word: words) {
            if (word.equalsIgnoreCase(pattern)) {
                count += 1;
            }
        }
        return ResponseEntity.ok("'" + text.getText() + "' contains " + count + " occurrences of word 'no'");
    }
}
