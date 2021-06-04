package pl.tul.emailsemotions.emotionsservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.emotionsservice.services.HistoryService;

@RestController
@AllArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping(value = "/history/{userId}")
    @ResponseBody
    @CrossOrigin(value = "*")
    public ResponseEntity getUserHistory(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(historyService.getUserHistory(userId));
    }
}
