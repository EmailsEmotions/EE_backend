package pl.tul.emailsemotions.apigateway.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tul.emailsemotions.apigateway.auth.impl.AuthenticationFilter;

@Configuration
@EnableHystrix
public class GatewayConfig {

    AuthenticationFilter filter;

    @Autowired
    public GatewayConfig(AuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("users-service", r -> r.path("/api/user/**")
                .filters(f -> f.filter(filter).stripPrefix(2))
                .uri("lb://users-service"))

            .route("formality-service", r -> r.path("/api/formality/**")
                .filters(f -> f.filter(filter).stripPrefix(2))
                .uri("lb://formality-service"))

            .route("emotions-service", r -> r.path("/api/emotions/**")
                .filters(f -> f.filter(filter).stripPrefix(2))
                .uri("lb://emotions-service"))

            .route("stats-service", r -> r.path("/api/stats/**")
                .filters(f -> f.filter(filter).stripPrefix(2))
                .uri("lb://stats-service"))

            .route("email-service", r -> r.path("/api/email/**")
                .filters(f -> f.filter(filter).stripPrefix(2))
                .uri("lb://email-service"))

            .route("ai-service", r -> r.path("/api/ai/**")
                .filters(f -> f.filter(filter).stripPrefix(2))
                .uri("lb://ai-service"))
                .build();
    }

}
