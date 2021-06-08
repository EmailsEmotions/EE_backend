package pl.tul.emailsemotions.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import java.util.UUID;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        System.setProperty("app_uuid", UUID.randomUUID().toString());
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
