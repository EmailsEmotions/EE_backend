package pl.tul.emailsemotions.formalityservice.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.formalityservice.model.FormalityEvaluation;
import pl.tul.emailsemotions.formalityservice.services.EvaluationService;

@RestController
@AllArgsConstructor
public class EvaluationController {
    private final EvaluationService evaluationService;

    @PostMapping(value = "/evaluate")
    @ResponseBody
    @CrossOrigin(value = "*")
    public ResponseEntity evaluateRecognition(@RequestBody FormalityEvaluation formalityEvaluation) {
        try {
            return ResponseEntity.ok(evaluationService.add(formalityEvaluation));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}