package pl.tul.emailsemotions.formalityservice.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.formalityservice.services.TextService;

@RestController
@AllArgsConstructor
public class TextController {
    private final TextService textService;

    @GetMapping("/text/{id}")
    @ResponseBody
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(textService.get(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/text")
    @ResponseBody
    public ResponseEntity getAll() {
        return ResponseEntity.ok(textService.getAll());
    }
}
