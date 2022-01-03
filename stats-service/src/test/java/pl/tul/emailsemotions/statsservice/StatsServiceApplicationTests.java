package pl.tul.emailsemotions.statsservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.spring.web.plugins.Docket;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StatsServiceApplicationTests {

    @Test
    void swaggerConfigTest() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        Docket api = swaggerConfig.api();
        assertThat(api.getDocumentationType().toString()).isEqualTo("openApi:3.0");
        assertThat(api.getGroupName()).isEqualTo("default");
    }
}
