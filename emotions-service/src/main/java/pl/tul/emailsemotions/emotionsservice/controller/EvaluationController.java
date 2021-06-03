package pl.tul.emailsemotions.emotionsservice.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.emotionsservice.model.EmotionsEvaluation;
import pl.tul.emailsemotions.emotionsservice.services.EvaluationService;

@RestController
@AllArgsConstructor
public class EvaluationController {
    private final EvaluationService evaluationService;

    @PostMapping(value = "/evaluate")
    @ResponseBody
    @CrossOrigin(value = "*")
    public ResponseEntity evaluateRecognition(@RequestBody EmotionsEvaluation emotionsEvaluation) {
        try {
            return ResponseEntity.ok(evaluationService.add(emotionsEvaluation));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
