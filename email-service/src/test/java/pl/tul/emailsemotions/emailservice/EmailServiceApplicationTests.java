package pl.tul.emailsemotions.emailservice;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmailServiceApplicationTests {

    @Value("classpath:templates/template.html")
    private Resource resource;

    @Test
    void mailTemplateTest() {
            try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
                String template = FileCopyUtils.copyToString(reader);
                assertThat(template).contains("EmailsEmotions Message");
                assertThat(template).contains("Wiadomość wysłana z systemu EmailsEmotions");
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
    }

    @Test
    void swaggerConfigTest() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        Docket api = swaggerConfig.api();
        assertThat(api.getDocumentationType().toString()).isEqualTo("openApi:3.0");
        assertThat(api.getGroupName()).isEqualTo("default");
    }

}
