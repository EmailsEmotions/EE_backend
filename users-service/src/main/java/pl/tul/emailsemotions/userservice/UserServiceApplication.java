package pl.tul.emailsemotions.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.UUID;

@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        System.setProperty("app_uuid", UUID.randomUUID().toString());
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
