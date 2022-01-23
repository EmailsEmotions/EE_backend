package pl.tul.emailsemotions.apigateway.auth.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.shared.api.User;
import pl.tul.emailsemotions.apigateway.auth.interfaces.AuthService;
import pl.tul.emailsemotions.apigateway.auth.interfaces.BasicUserService;
import pl.tul.emailsemotions.apigateway.auth.models.UserData;
import pl.tul.emailsemotions.shared.exceptions.IllegalActionException;
import pl.tul.emailsemotions.shared.exceptions.UnauthorizedActionException;
import pl.tul.emailsemotions.shared.exceptions.WrongArgumentException;

@Service("authService")
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final BasicUserService basicUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(JwtUtil jwtUtil,
                           BasicUserService basicUserService,
                           PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.basicUserService = basicUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Pair<User, String> authenticate(String username, String rawPassword) {
        UserData userData = basicUserService.findUserDataByUsername(username);
        if (!passwordEncoder.matches(rawPassword, userData.getPassword())) {
            throw new UnauthorizedActionException("Bad credentials");
        }
        if (!userData.isActive()) {
            throw new UnauthorizedActionException("Account is disabled!");
        }
        String jwtToken = jwtUtil.generateToken(username);
        return Pair.of(userData.toUser(), jwtToken);
    }

}
