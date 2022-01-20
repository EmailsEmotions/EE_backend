package pl.tul.emailsemotions.statsservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.shared.api.AccountType;
import pl.tul.emailsemotions.shared.verify.AccountTypeVerifier;
import pl.tul.emailsemotions.statsservice.services.FormalityStatisticsService;

@RestController
@AllArgsConstructor
public class FormalityStatisticsController {
    private final FormalityStatisticsService formalityStatisticsService;

    @GetMapping("/formality/{userId}")
    @ResponseBody
    public ResponseEntity calculateFormalityStatistics(@RequestHeader String loggedUserId,
                                                       @RequestHeader String loggedUserRole,
                                                       @PathVariable("userId") Long userId) {
        if (!Long.valueOf(loggedUserId).equals(userId)) {
            AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        }
        return ResponseEntity.ok(formalityStatisticsService.calculateFormalityStatistics(userId));
    }
}
