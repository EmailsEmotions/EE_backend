package pl.tul.emailsemotions.apigateway.auth.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.api.User;
import pl.tul.emailsemotions.apigateway.auth.interfaces.AuthService;
import pl.tul.emailsemotions.apigateway.auth.interfaces.JwtUtils;
import pl.tul.emailsemotions.apigateway.auth.interfaces.UserSessionService;
import pl.tul.emailsemotions.exceptions.IllegalActionException;

@Service("authService")
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final UserSessionService userSessionService;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtUtils jwtUtils,
                           UserDetailsService userDetailsService,
                           UserSessionService userSessionService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.userSessionService = userSessionService;
    }

    @Override
    public Pair<User, String> authenticate(String username, String password) {
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);
        if (!userDetails.isEnabled()) {
            throw new IllegalActionException("Account is disabled!");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtils.generateJwtToken(authentication);
        User user = userSessionService.getAuthenticatedUser();
        return Pair.of(user, jwtToken);
    }
}
