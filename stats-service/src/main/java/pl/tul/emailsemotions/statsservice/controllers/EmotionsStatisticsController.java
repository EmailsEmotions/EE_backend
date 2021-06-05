package pl.tul.emailsemotions.statsservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.statsservice.services.EmotionsStatisticsService;
import pl.tul.emailsemotions.statsservice.services.FormalityStatisticsService;

@RestController
@AllArgsConstructor
public class EmotionsStatisticsController {
    private final EmotionsStatisticsService emotionsStatisticsService;

    @GetMapping("/emotions/{userId}")
    @ResponseBody
    @CrossOrigin(value = "*")
    public ResponseEntity calculateFormalityStatistics(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(emotionsStatisticsService.calculateEmotionsStatistics(userId));
    }
}
