package pl.tul.emailsemotions.emotionsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EmotionsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmotionsServiceApplication.class, args);
    }

}
