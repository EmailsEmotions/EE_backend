package pl.tul.emailsemotions.emotionsservice;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Configuration
public class AppProperties {

    private final static UUID app_id = UUID.randomUUID();
    @PostConstruct
    private void init() {
        System.setProperty("app_uuid", app_id.toString());
    }
}
