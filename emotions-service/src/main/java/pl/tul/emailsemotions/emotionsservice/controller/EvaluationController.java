package pl.tul.emailsemotions.emotionsservice.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.emotionsservice.model.EmotionsEvaluation;
import pl.tul.emailsemotions.emotionsservice.services.EvaluationService;
import pl.tul.emailsemotions.emotionsservice.services.TextService;
import pl.tul.emailsemotions.shared.api.AccountType;
import pl.tul.emailsemotions.shared.verify.AccountTypeVerifier;

import java.util.LinkedList;
import java.util.List;

@RestController
@AllArgsConstructor
public class EvaluationController {
    private final EvaluationService evaluationService;

    @PostMapping(value = "/evaluate")
    @ResponseBody
    public ResponseEntity evaluateRecognition(@RequestBody EmotionsEvaluation emotionsEvaluation,
                                              @RequestHeader String loggedUserId,
                                              @RequestHeader String loggedUserRole) {
        if (!Long.valueOf(loggedUserId).equals(emotionsEvaluation.getUserId())) {
            AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        }
        try {
            return ResponseEntity.ok(evaluationService.add(emotionsEvaluation));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/evaluations/{userId}")
    @ResponseBody
    public ResponseEntity getAllByUserId(@RequestHeader String loggedUserId,
                                         @RequestHeader String loggedUserRole,
                                         @PathVariable("userId") Long userId) {
        if (!Long.valueOf(loggedUserId).equals(userId)) {
            AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        }
        return ResponseEntity.ok(evaluationService.getAllByUserId(userId));
    }
}
