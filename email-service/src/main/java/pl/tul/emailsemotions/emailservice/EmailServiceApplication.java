package pl.tul.emailsemotions.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.UUID;

@SpringBootApplication
@EnableDiscoveryClient
public class EmailServiceApplication {

    public static void main(String[] args) {
        System.setProperty("app_uuid", UUID.randomUUID().toString());
        SpringApplication.run(EmailServiceApplication.class, args);
    }

}
