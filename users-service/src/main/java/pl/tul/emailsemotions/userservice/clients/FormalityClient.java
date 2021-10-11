package pl.tul.emailsemotions.userservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.tul.emailsemotions.userservice.clients.models.BaseText;
import pl.tul.emailsemotions.userservice.clients.models.formality.FormalityText;

import java.util.List;
@Component
@FeignClient(value = "http://formality-service")
public interface FormalityClient {

    @RequestMapping(value="/text", method = RequestMethod.GET)
    List<BaseText> getAllBase();

    @RequestMapping(value="/text", method = RequestMethod.GET)
    List<FormalityText> getAllFormality();
}
