package pl.tul.emailsemotions.emotionsservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.emotionsservice.services.HistoryService;
import pl.tul.emailsemotions.shared.api.AccountType;
import pl.tul.emailsemotions.shared.verify.AccountTypeVerifier;

@RestController
@AllArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping(value = "/history/{userId}")
    @ResponseBody
    public ResponseEntity getUserHistory(@RequestHeader String loggedUserId,
                                         @RequestHeader String loggedUserRole,
                                         @PathVariable("userId") Long userId) {
        if (!Long.valueOf(loggedUserId).equals(userId)) {
            AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        }
        return ResponseEntity.ok(historyService.getUserHistory(userId));
    }
}
