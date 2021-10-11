# Links for different topics regarding project
Here you can found informations about diffrent topics
* Spring Admin
* Configuration Server
* Eureka
* Config Client
* Feign Client
* API Gateway
* Grafana
* Sleuth Zipkin
* Prometheus
* Docker configuration
* DB configuration
* Email
* Swagger UI

# Spring Admin
https://codecentric.github.io/spring-boot-admin/2.3.1/#_what_is_spring_boot_admin

## Configuration
https://docs.spring.io/spring-boot/docs/1.5.22.RELEASE/reference/html/boot-features-external-config.html
https://stackoverflow.com/questions/49274032/spring-boot-configuration-how-to-return-always-same-random-value-when-reference

# Spring Eureka Discovery
https://cloud.spring.io/spring-cloud-netflix/multi/multi__service_discovery_eureka_clients.html
## Microservices instances
### baeldung
https://www.baeldung.com/eureka-self-preservation-renewal
### Stack
https://stackoverflow.com/questions/52297664/spring-cloud-eureka-server-self-preservation-and-renew-threshold
https://stackoverflow.com/questions/54468805/eureka-client-instances-are-not-unregister-from-eureka-server-properly
https://stackoverflow.com/questions/33921557/understanding-spring-cloud-eureka-server-self-preservation-and-renew-threshold
https://stackoverflow.com/questions/32616329/eureka-never-unregisters-a-service
## Feign Clients
https://www.baeldung.com/spring-cloud-netflix-eureka

# Spring Cloud Config Client
https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_client.html


# Api Gateway
Circuit Breaker Pattern ->
https://www.youtube.com/watch?v=5_Bt_OEg0no


#Grafana
Todo

#Sleuth & Zipkin
Todo

# Prometheus
TODO

Some metrics file
```
  metrics:
    distribution:
      percentiles-histogram.http.server.requests: false
      percentiles.http.server.requests: 0.5, 0.9, 0.95, 0.99, 0.999
      sla.http.server.requests: 500ms, 2000ms
```

# Docker configuration
How to start applications properly

https://stackoverflow.com/questions/31746182/docker-compose-wait-for-container-x-before-starting-y


# Database configuration
Running container with mysql image:
```
docker run -d -e MYSQL_ROOT_PASSWORD=emailsemotions -e MYSQL_DATABASE=formality -p 3306:3306 mysql:5.7.8
```
https://nickjanetakis.com/blog/docker-tip-45-docker-compose-stop-vs-down

JPA Column configuration problems: https://stackoverflow.com/questions/25283198/spring-boot-jpa-column-name-annotation-ignored

Just rename all your `@Column` to lowercase

# Email
Email is setup for `emailsemotions@gmail.com`

# Swagger UI
Swagger is on `service:port/swagger-ui/index.html`
https://stackoverflow.com/questions/46151540/added-springfox-swagger-ui-and-its-not-working-what-am-i-missing

https://stackoverflow.com/questions/32194250/unable-to-bring-up-swagger-ui-from-spring-boot-

https://stackoverflow.com/questions/23767886/not-able-to-get-swagger-ui-with-spring-boot

https://stackoverflow.com/questions/53453006/swagger-ui-html-page-not-working-springboot

https://stackoverflow.com/questions/62845550/swagger-api-documentation-in-spring-api-gateway

## Combining swagger
https://blog.wick.technology/distributed-api-docs/
https://stackoverflow.com/questions/48646068/how-can-i-aggregate-microservices-swaggers-into-a-single-swagger
https://github.com/varghgeorge/microservices-single-swagger
https://objectpartners.com/2017/09/28/aggregate-services-into-a-single-swagger/
