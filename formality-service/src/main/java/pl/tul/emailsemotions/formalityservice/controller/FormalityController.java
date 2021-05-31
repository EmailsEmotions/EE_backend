package pl.tul.emailsemotions.formalityservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.formalityservice.model.Text;
import pl.tul.emailsemotions.formalityservice.services.FormalityService;

@RestController
@AllArgsConstructor
public class FormalityController {
    private final FormalityService formalityService;

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

    @PostMapping("/recognize")
    @ResponseBody
    @CrossOrigin(value = "*")
    public ResponseEntity recognizeFormality(@RequestBody Text text) {
        return ResponseEntity.ok(formalityService.recognizeFormality(text));
    }
}
