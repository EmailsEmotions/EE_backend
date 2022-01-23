package pl.tul.emailsemotions.formalityservice.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.formalityservice.clients.UserClient;
import pl.tul.emailsemotions.formalityservice.clients.model.User;
import pl.tul.emailsemotions.formalityservice.model.FormalityEvaluation;
import pl.tul.emailsemotions.formalityservice.services.EvaluationService;
import pl.tul.emailsemotions.shared.api.AccountType;
import pl.tul.emailsemotions.shared.verify.AccountTypeVerifier;

@RestController
@AllArgsConstructor
public class EvaluationController {
    private final EvaluationService evaluationService;
    private final UserClient userClient;

    @PostMapping(value = "/evaluate")
    @ResponseBody
    public ResponseEntity evaluateRecognition(@RequestBody FormalityEvaluation formalityEvaluation,
                                              @RequestHeader String loggedUserId,
                                              @RequestHeader String loggedUserRole) {
        if (!Long.valueOf(loggedUserId).equals(formalityEvaluation.getUserId())) {
            AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        }
        try {
            return ResponseEntity.ok(evaluationService.add(formalityEvaluation));
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

    @GetMapping(value = "/user/{userId}")
    public User findUser(@PathVariable("userId") Long userId) {
        return userClient.getUser(userId);
    }
}
