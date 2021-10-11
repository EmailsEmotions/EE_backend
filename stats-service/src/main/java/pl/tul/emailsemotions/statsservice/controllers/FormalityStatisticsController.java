package pl.tul.emailsemotions.statsservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.statsservice.services.FormalityStatisticsService;

@RestController
@AllArgsConstructor
public class FormalityStatisticsController {
    private final FormalityStatisticsService formalityStatisticsService;

    @GetMapping("/formality/{userId}")
    @ResponseBody
    public ResponseEntity calculateFormalityStatistics(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(formalityStatisticsService.calculateFormalityStatistics(userId));
    }
}
