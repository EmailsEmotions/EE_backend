package pl.tul.emailsemotions.apigateway.auth.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service("authenticationEntryPoint")
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String errorMessage = authException.getMessage();
        LOGGER.error("Unauthorized error: {}", errorMessage);
        if (authException instanceof BadCredentialsException) {
            errorMessage = "Bad credentials";
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);
    }
}
