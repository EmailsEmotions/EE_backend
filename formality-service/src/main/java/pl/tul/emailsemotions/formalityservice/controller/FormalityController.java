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

    @PostMapping("/recognize")
    @ResponseBody
    @CrossOrigin(value = "*")
    public ResponseEntity recognizeFormality(@RequestBody Text text) {
        return ResponseEntity.ok(formalityService.recognizeFormality(text));
    }
}
