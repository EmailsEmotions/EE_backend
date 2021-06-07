package pl.tul.emailsemotions.userservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.tul.emailsemotions.userservice.clients.models.MailObject;

@Component
@FeignClient(value = "http://email-service")
public interface MailClient {
    @RequestMapping(value="/sendMail", method = RequestMethod.POST)
    String send(@RequestBody MailObject mailObject);
}
