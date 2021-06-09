# Creating microservice
You have to choose at least these dependencies when creating new module.
![readme/springboot-dependencies.png](readme/springboot-dependencies.png)
* Spring Web - for constructing spring APIs
* Lombok - for bolierplate code
* Spring Configuration Processor
* Spring Boot Actuator - Custom metrics
* Config Client - Access to config server
* Eureka Discovery Client - Eureka Cilent

Then remember to add to `pom` that dependency. It allows to read `bootstrap.yml`. Which is neccessary in our configuration.
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```
Next add `bootstrap.yml` with these configuration properties.
```yaml
spring:
  cloud:
    config:
      uri: http://localhost:8888
  application:
    name: user-service
---
spring:
  config:
    activate:
      on-profile: docker
  cloud:
    config:
      uri: http://config-server:8888
```
You have to give url for `spring.cloud.config.uri` that corresponds to config server
