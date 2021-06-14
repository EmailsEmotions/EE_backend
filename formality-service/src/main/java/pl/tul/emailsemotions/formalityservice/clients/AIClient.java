package pl.tul.emailsemotions.formalityservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.formalityservice.clients.model.AIResult;
import pl.tul.emailsemotions.formalityservice.clients.model.User;

import java.util.List;

@Component
@FeignClient(value = "http://ai-service")
public interface AIClient {

    @PostMapping(path= "/countFormal", consumes = "text/plain; charset: utf-8", produces = MediaType.APPLICATION_JSON_VALUE)
    AIResult countFormal(@RequestBody String text);
}
