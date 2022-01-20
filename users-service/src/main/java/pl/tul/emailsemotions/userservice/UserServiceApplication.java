package pl.tul.emailsemotions.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.UUID;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"pl.tul.emailsemotions"})
@EnableFeignClients
public class UserServiceApplication {

    public static void main(String[] args) {
        System.setProperty("app_uuid", UUID.randomUUID().toString());
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
