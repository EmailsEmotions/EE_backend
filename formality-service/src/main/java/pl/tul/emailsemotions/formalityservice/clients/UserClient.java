package pl.tul.emailsemotions.formalityservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.tul.emailsemotions.formalityservice.clients.model.User;

import java.util.List;

@Component
@FeignClient(value = "http://users-service")
public interface UserClient {

    @RequestMapping(value="/", method = RequestMethod.GET)
    List<User> getAllUsers();

    @RequestMapping(value="/{userid}", method = RequestMethod.GET)
    User getUser(@PathVariable("userid") Long userid);
}
