package pl.tul.emailsemotions.apigateway.auth.rest.controller;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pl.tul.emailsemotions.api.User;
import pl.tul.emailsemotions.apigateway.auth.interfaces.AuthService;
import pl.tul.emailsemotions.apigateway.auth.interfaces.BlackListService;
import pl.tul.emailsemotions.apigateway.auth.rest.request.LoginRequest;
import pl.tul.emailsemotions.apigateway.auth.rest.response.LoginResponse;

@RestController
public class AuthRestController {

    private final AuthService authService;
    private final BlackListService blackListService;

    @Autowired
    public AuthRestController(AuthService authService,
                              BlackListService blackListService) {
        this.authService = authService;
        this.blackListService = blackListService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Pair<User, String> userWithToken = authService.authenticate(
                loginRequest.getUsername(), loginRequest.getPassword());
        String token = userWithToken.getRight();
        User user = userWithToken.getLeft();
        return ResponseEntity.ok(LoginResponse.builder()
                .user(user)
                .token(token)
                .build());
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<String> logout(@RequestHeader(value="Authorization")  String authorization) {
        blackListService.addTokenFromHeader(authorization);
        return ResponseEntity.ok("Successfully user logout");
    }
}
