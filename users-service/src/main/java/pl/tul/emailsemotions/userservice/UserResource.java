package pl.tul.emailsemotions.userservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.userservice.clientModels.Text;
import pl.tul.emailsemotions.userservice.clients.FormalityClient;
import pl.tul.emailsemotions.userservice.model.User;
import pl.tul.emailsemotions.userservice.model.UserRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequestMapping("/users")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserResource {

    private final UserRepository userRepository;
    private final FormalityClient formalityClient;

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{userId}")
    public User findUser(@PathVariable("userId") int userId) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 999 + 1);
        log.info("User {} fetched", String.valueOf(randomNum));
        return new User(1L,"username","email@abc.pl","de");
//        return userRepository.findById(userId);
    }
    @GetMapping("/getalltext")
    @ResponseBody
    public List<Text> getAll() {
        return formalityClient.getAll();
    }
}
