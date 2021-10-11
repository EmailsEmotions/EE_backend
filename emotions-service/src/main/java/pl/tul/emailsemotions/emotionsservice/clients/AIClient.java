package pl.tul.emailsemotions.emotionsservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.tul.emailsemotions.emotionsservice.clients.models.AIResult;

@Component
@FeignClient(value = "http://ai-service")
public interface AIClient {

    @PostMapping(path= "/countEmotions", consumes = "text/plain; charset: utf-8", produces = MediaType.APPLICATION_JSON_VALUE)
    AIResult countEmotions(@RequestBody String text);
}
