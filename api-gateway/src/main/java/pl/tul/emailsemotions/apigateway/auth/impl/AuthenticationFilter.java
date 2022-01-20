package pl.tul.emailsemotions.apigateway.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pl.tul.emailsemotions.apigateway.configs.RouterValidatorConfig;
import pl.tul.emailsemotions.shared.api.User;
import pl.tul.emailsemotions.apigateway.auth.interfaces.BasicUserService;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    private final RouterValidatorConfig routerValidator;
    private final JwtUtil jwtUtil;
    private final BasicUserService basicUserService;

    @Autowired
    public AuthenticationFilter(RouterValidatorConfig routerValidator,
                                JwtUtil jwtUtil,
                                BasicUserService basicUserService) {
        this.routerValidator = routerValidator;
        this.jwtUtil = jwtUtil;
        this.basicUserService = basicUserService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routerValidator.isSecured.test(request)) {
            if (isAuthMissing(request)) {
                return onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
            }
            final String token = getAuthHeader(request);

            if (jwtUtil.isInvalid(token)) {
                return onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
            }
            populateRequestWithHeaders(exchange, token);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        String username = jwtUtil.getUsernameFromJwtToken(token);
        User user = basicUserService.findUserByUsername(username);
        exchange.getRequest().mutate()
                .header("loggedUserId", user.getId().toString())
                .header("loggedUserRole", user.getAccountType().toString())
                .build();
    }
}
