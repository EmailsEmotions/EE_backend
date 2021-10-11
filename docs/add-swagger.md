# How to add Swagger to SpringBoot

## Pom
Add this dependency to pom, version >= 3.0.0
```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
```
## Java
Make sure your app is SpringBoot Application (main class, with annotation `@SpringBootApplication`)

Create `SwaggerConfig` class with this code:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
            .select()
//          .apis(RequestHandlerSelectors.any()) ...etc...    
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
            .title("Emails Emotions Email Microservice")
            .version("1.0")
            .description("API for managing Emails Emotions Email Microservice.")
            .license("Apache 2.0")
            .contact(getContact())
            .build();
    }
    private Contact getContact() {
        return new Contact("EE Team",
            "https://github.com/EmailsEmotions/",
            "emailsemotions@gmail.com");
    }
}
```

It enables *Swagger* and *Swagger UI* on http://localhost:8080/swagger-ui/index.html
