package pl.tul.emailsemotions.formalityservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.formalityservice.model.Text;
import pl.tul.emailsemotions.formalityservice.services.FormalityService;
import pl.tul.emailsemotions.shared.api.AccountType;
import pl.tul.emailsemotions.shared.verify.AccountTypeVerifier;

@RestController
@AllArgsConstructor
public class FormalityController {
    private final FormalityService formalityService;

    @PostMapping("/recognize")
    @ResponseBody
    public ResponseEntity recognizeFormality(@RequestBody Text text,
                                             @RequestHeader String loggedUserId,
                                             @RequestHeader String loggedUserRole) {
        if (!Long.valueOf(loggedUserId).equals(text.getUserId())) {
            AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        }
        return ResponseEntity.ok(formalityService.recognizeFormality(text));
    }

    @GetMapping("/recognitions/{userId}")
    @ResponseBody
    public ResponseEntity getAllByUserId(@RequestHeader String loggedUserId,
                                         @RequestHeader String loggedUserRole,
                                         @PathVariable("userId") Long userId) {
        if (!Long.valueOf(loggedUserId).equals(userId)) {
            AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        }
        return ResponseEntity.ok(formalityService.getAllByUserId(userId));
    }
}
