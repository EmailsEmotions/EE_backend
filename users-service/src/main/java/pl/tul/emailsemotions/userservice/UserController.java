package pl.tul.emailsemotions.userservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tul.emailsemotions.userservice.clientModels.BaseText;
import pl.tul.emailsemotions.userservice.clientModels.emotions.EmotionsText;
import pl.tul.emailsemotions.userservice.clientModels.formality.FormalityText;
import pl.tul.emailsemotions.userservice.clients.EmotionsClient;
import pl.tul.emailsemotions.userservice.clients.FormalityClient;
import pl.tul.emailsemotions.userservice.model.AccountType;
import pl.tul.emailsemotions.userservice.model.User;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FormalityClient formalityClient;
    private final EmotionsClient emotionsClient;

    @GetMapping
    public List<User> findAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/find")
    public User findUser(@RequestParam("username") String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/addUser")
    public ResponseEntity add(@RequestBody User user) {
        return ResponseEntity.ok(userService.add(user));
    }

    @GetMapping(value = "/{userId}")
    public User findUser(@PathVariable("userId") Long userId) {
        return userService.get(userId);
    }

    @PostMapping(value = "/{userId}/activate")
    public ResponseEntity activateUser(@PathVariable("userId") Long userId) {
        try {
            userService.changeActiveStatus(userId, true);
            HashMap<String, String> body = new HashMap<>();
            body.put("active", "true");
            return ResponseEntity.ok(body);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/{userId}/deactivate")
    public ResponseEntity deactivateUser(@PathVariable("userId") Long userId) {
        try {
            userService.changeActiveStatus(userId, false);
            HashMap<String, String> body = new HashMap<>();
            body.put("active", "false");
            return ResponseEntity.ok(body);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/{userId}/changeAccountType")
    public ResponseEntity changeAccountType(@PathVariable("userId") Long userId, @RequestParam("accounttype") AccountType accountType) {
        try {
            userService.changeAccountType(userId, accountType);
            HashMap<String, String> body = new HashMap<>();
            body.put("accountType", accountType.toString());
            return ResponseEntity.ok(body);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
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

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        try {
            Boolean isLogged = userService.login(loginDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", ex.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
            Map.of("logged", "ok"));
    }
}
