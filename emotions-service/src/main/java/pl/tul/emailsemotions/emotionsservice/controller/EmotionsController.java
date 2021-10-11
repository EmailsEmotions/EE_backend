package pl.tul.emailsemotions.emotionsservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.emotionsservice.model.Text;
import pl.tul.emailsemotions.emotionsservice.services.EmotionsService;

@RestController
@AllArgsConstructor
public class EmotionsController {
    private final EmotionsService emotionsService;

    @PostMapping("/recognize")
    @ResponseBody
    public ResponseEntity recognizeEmotions(@RequestBody Text text) {
        return ResponseEntity.ok(emotionsService.recognizeFormality(text));
    }

    @GetMapping("/recognitions/{userId}")
    @ResponseBody
    public ResponseEntity getAllByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(emotionsService.getAllByUserId(userId));
    }
}
