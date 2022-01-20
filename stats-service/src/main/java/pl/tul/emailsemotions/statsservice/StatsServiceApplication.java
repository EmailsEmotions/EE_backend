package pl.tul.emailsemotions.statsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.UUID;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"pl.tul.emailsemotions"})
@EnableFeignClients
public class StatsServiceApplication {

    public static void main(String[] args) {
        System.setProperty("app_uuid", UUID.randomUUID().toString());
        SpringApplication.run(StatsServiceApplication.class, args);
    }

}
