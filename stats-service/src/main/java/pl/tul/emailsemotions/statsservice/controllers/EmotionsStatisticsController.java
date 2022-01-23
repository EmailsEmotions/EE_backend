package pl.tul.emailsemotions.statsservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.tul.emailsemotions.shared.api.AccountType;
import pl.tul.emailsemotions.shared.verify.AccountTypeVerifier;
import pl.tul.emailsemotions.statsservice.services.EmotionsStatisticsService;

@RestController
@AllArgsConstructor
public class EmotionsStatisticsController {
    private final EmotionsStatisticsService emotionsStatisticsService;

    @GetMapping("/emotions/{userId}")
    @ResponseBody
    public ResponseEntity calculateFormalityStatistics(@RequestHeader String loggedUserId,
                                                       @RequestHeader String loggedUserRole,
                                                       @PathVariable("userId") Long userId) {
        if (!Long.valueOf(loggedUserId).equals(userId)) {
            AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        }
        return ResponseEntity.ok(emotionsStatisticsService.calculateEmotionsStatistics(userId));
    }
}
