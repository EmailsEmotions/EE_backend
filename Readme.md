# EmailsEmotions microservice backend

# Important informations
# Running
Build all artifacts and run
```shell
docker-compose -f docker-compose.yaml -f docker-compose-ai.yaml up --build -d
```
## Docker
Make sure to run `docker-compose stop` or `docker-compose down` or `docker-compose down -v` 
or `docker-compose rm` to clear your working environment.
`docker-compose down --remove`
`docker-compose -f databases-docker-compose.yaml down`

When something is not working for example database ;)

Important flags & commands:
* `docker-compose -f databases-docker-compose.yaml up --build --remove-orphans`
    * _--remove-orphans_ : Removes all containers left by docker-compose
* `docker-compose -f databases-docker-compose.yaml down --v`
    * _--v or -volumes_ : Removes all volumes left by containers
* `docker-compose -f docker-compose.yaml up --build users-service -d`
  * _users-service -d_ : Rebuilds only one service from compose (and all that will be depending)
# Run & Build
Most important is the `config-server` and order of startup for different applications.
Supporting services (Config and Discovery Server) must be started before any other application (Admin, Users, Formality, API, etc).

Main access point is API Gateway on - http://localhost:8080

All services have configured Swagger UI on `http://service:port/swagger-ui/index.html`
## IntelliJ
Import whole project by choosing main `.pom`.
Make sure you cloned [Configuration repository].
```shell
git clone https://github.com/EmailsEmotions/EmailsEmotionsConfiguration
```
Setup path to this repository in `config-server/src/main/resources/bootstrap.yml`.
```yaml
searchLocations: file:///ABSOLUTE_PATH_TO_CONFIG_REPOSITORY
```
Next you have to run databases
```shell
docker-compose -f databases-docker-compose.yaml up --build --remove-orphans
```
optionally ELK stack
```shell
docker-compose -f elk-docker-compose.yaml up --build --remove-orphans
```
## Docker
You have to build all `.jars` with `mvn package` then you can execute
```shell
$ docker-compose -f docker-compose --build -d
```

## Docker BuildKit
To speed up build process when not using `jars` use Docker BuildKit

`https://docs.docker.com/develop/develop-images/build_enhancements/`

[Stack overflow: Maven docker cache dependencies](https://stackoverflow.com/questions/42208442/maven-docker-cache-dependencies)

Windows
```commandline
> setx DOCKER_BUILDKIT 1
> setx COMPOSE_DOCKER_CLI_BUILD 1
> docker-compose -f docker-compose-with-maven.yaml up --build -d
> docker-compose -f docker-compose.yaml up --build -d
> docker-compose -f docker-compose.yaml up -f docker-compose-ai.yaml --build -d
```
Linux
```shell
$ export DOCKER_BUILDKIT=1 # or configure in daemon.json
$ export COMPOSE_DOCKER_CLI_BUILD=1
$ docker-compose -f docker-compose-with-maven.yaml up --build -d
$ docker-compose -f docker-compose.yaml up --build -d
$ docker-compose -f docker-compose.yaml -f docker-compose-ai.yaml up --build -d
```
## Git Flow Setup
```shell
git flow init [Enter]x5
```
More informations here https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow

## AI-module
To use AI module you have to clone `https://github.com/EmailsEmotions/EE_AI.git`

And build image with tag ai-docker
```shell
docker build -t ai-docker .
```

# Additional informations

| Components                      | Resources  |
|---------------------------------|------------|
| Config Server Setup             | [Config server properties](config-server/src/main/resources/bootstrap.yml) and [Configuration repository] |
| Service Discovery               | [Eureka server](discovery-server) and [Service discovery client](email-service/src/main/java/pl/tul/emailsemotions/emailservice/EmailServiceApplication.java) |
| API Gateway                     | [Spring Cloud Gateway starter](api-gateway/pom.xml) and [Routing configuration](api-gateway/src/main/resources/application.yml) |
| Docker Compose                  | [Spring Boot with Docker guide](https://spring.io/guides/gs/spring-boot-docker/) and [docker-compose file](docker-compose.yaml) | 
| ELK stack                       | [ELK stack](docs/elk-stack.md) and [ELK docker-compose](elk-docker-compose.yaml)
| Databases stack                 | [DB docker-compose](databases-docker-compose.yaml)
| Jmeter                          | [JMeter startup](docs/jmeter.md)
| Creating microservice           | [Docs on creating microservice](docs/creating-microservice.md)
| Swagger Config                  | [Docs on adding Swagger to service](docs/add-swagger.md)
[Configuration repository]: https://github.com/EmailsEmotions/EmailsEmotionsConfiguration
