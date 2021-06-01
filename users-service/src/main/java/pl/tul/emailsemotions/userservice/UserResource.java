package pl.tul.emailsemotions.userservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.userservice.clientModels.BaseText;
import pl.tul.emailsemotions.userservice.clientModels.emotions.EmotionsText;
import pl.tul.emailsemotions.userservice.clientModels.formality.FormalityText;
import pl.tul.emailsemotions.userservice.clients.EmotionsClient;
import pl.tul.emailsemotions.userservice.clients.FormalityClient;
import pl.tul.emailsemotions.userservice.model.User;
import pl.tul.emailsemotions.userservice.model.UserRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("/users")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserResource {

    private final UserRepository userRepository;
    private final FormalityClient formalityClient;
    private final EmotionsClient emotionsClient;

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{userId}")
    public User findUser(@PathVariable("userId") int userId) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 999 + 1);
        log.info("User {} fetched", String.valueOf(randomNum));
        return new User(1L, "username", "email@abc.pl", "de");
//        return userRepository.findById(userId);
    }

    @GetMapping("/formalitytexts")
    @ResponseBody
    public List<FormalityText> getAllFormalityTexts() {
        return formalityClient.getAllFormality();
    }

    @GetMapping("/emotionstexts")
    @ResponseBody
    public List<EmotionsText> getAllEmotionsTexts() {
        return emotionsClient.getAllEmotions();
    }

    @GetMapping("/alltexts")
    @ResponseBody
    public List<BaseText> getAll() {
        return Stream.concat(
            emotionsClient.getAllBase().stream(),
            formalityClient.getAllBase().stream())
            .collect(Collectors.toList());
    }
}
