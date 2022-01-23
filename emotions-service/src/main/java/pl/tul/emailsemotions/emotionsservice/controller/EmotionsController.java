package pl.tul.emailsemotions.emotionsservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.emotionsservice.model.Text;
import pl.tul.emailsemotions.emotionsservice.services.EmotionsService;
import pl.tul.emailsemotions.shared.api.AccountType;
import pl.tul.emailsemotions.shared.verify.AccountTypeVerifier;

@RestController
@AllArgsConstructor
public class EmotionsController {
    private final EmotionsService emotionsService;

    @PostMapping("/recognize")
    @ResponseBody
    public ResponseEntity recognizeEmotions(@RequestBody Text text,
                                            @RequestHeader String loggedUserId,
                                            @RequestHeader String loggedUserRole) {
        if (!Long.valueOf(loggedUserId).equals(text.getUserId())) {
            AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        }
        return ResponseEntity.ok(emotionsService.recognizeFormality(text));
    }

    @GetMapping("/recognitions/{userId}")
    @ResponseBody
    public ResponseEntity getAllByUserId(@RequestHeader String loggedUserId,
                                         @RequestHeader String loggedUserRole,
                                         @PathVariable("userId") Long userId) {
        if (!Long.valueOf(loggedUserId).equals(userId)) {
            AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        }
        return ResponseEntity.ok(emotionsService.getAllByUserId(userId));
    }
}
