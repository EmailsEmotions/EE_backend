package pl.tul.emailsemotions.userservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.tul.emailsemotions.shared.api.AccountType;
import pl.tul.emailsemotions.shared.verify.AccountTypeVerifier;
import pl.tul.emailsemotions.userservice.clients.EmotionsClient;
import pl.tul.emailsemotions.userservice.clients.FormalityClient;
import pl.tul.emailsemotions.userservice.clients.models.BaseText;
import pl.tul.emailsemotions.userservice.clients.models.emotions.EmotionsText;
import pl.tul.emailsemotions.userservice.clients.models.formality.FormalityText;
import pl.tul.emailsemotions.userservice.dto.AddUserDTO;
import pl.tul.emailsemotions.userservice.model.User;
import pl.tul.emailsemotions.userservice.services.UsersService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UsersController {

    private final UsersService userService;
    private final FormalityClient formalityClient;
    private final EmotionsClient emotionsClient;

    @GetMapping(value = "/getAll")
    public List<User> findAll(@RequestHeader String loggedUserRole) {
        AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        return userService.getAll();
    }

    @GetMapping(value = "/find")
    public User findUser(@RequestHeader String loggedUserRole,
                         @RequestParam("username") String username) {
        AccountTypeVerifier.verifyUserRoleIsOnList(loggedUserRole, AccountType.ADMIN);
        return userService.findByUsername(username);
    }

    @PostMapping("/addUser")
    public ResponseEntity add(@Valid @RequestBody AddUserDTO user) {
        return ResponseEntity.ok(userService.add(user));
    }

    @GetMapping(value = "/{userId}")
    public User findUser(@RequestHeader String loggedUserId,
                         @RequestHeader String loggedUserRole,
                         @PathVariable("userId") Long userId) {
        if (!Long.valueOf(loggedUserId).equals(userId)) {
            AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        }
        return userService.get(userId);
    }

    @PostMapping(value = "/{userId}/activate")
    public ResponseEntity activateUser(@RequestHeader String loggedUserRole,
                                       @PathVariable("userId") Long userId) {
        AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        try {
            userService.changeActiveStatus(userId, true);
            HashMap<String, String> body = new HashMap<>();
            body.put("active", "true");
            return ResponseEntity.ok(body);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping(value = "/{userId}/deactivate")
    public ResponseEntity deactivateUser(@RequestHeader String loggedUserRole,
                                         @PathVariable("userId") Long userId) {
        AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        try {
            userService.changeActiveStatus(userId, false);
            HashMap<String, String> body = new HashMap<>();
            body.put("active", "false");
            return ResponseEntity.ok(body);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping(value = "/{userId}/changeAccountType")
    public ResponseEntity changeAccountType(@RequestHeader String loggedUserRole,
                                            @PathVariable("userId") Long userId,
                                            @RequestParam("accounttype") AccountType accountType) {
        AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        try {
            userService.changeAccountType(userId, accountType);
            HashMap<String, String> body = new HashMap<>();
            body.put("accountType", accountType.toString());
            return ResponseEntity.ok(body);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
        }
    }

    @GetMapping(value = "/{userId}/confirmAccount")
    public ResponseEntity confirmAccount(@PathVariable("userId") Long userId,
                                         @RequestHeader String loggedUserId,
                                         @RequestHeader String loggedUserRole) {
        if (!Long.valueOf(loggedUserId).equals(userId)) {
            AccountTypeVerifier.verifyUserRole(loggedUserRole, AccountType.ADMIN);
        }
        try {
            userService.changeConfirmedStatus(userId, true);
            HashMap<String, String> body = new HashMap<>();
            body.put("confirmed", "true");
            return ResponseEntity.ok(body);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
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
}
