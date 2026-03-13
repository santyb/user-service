##SDD Practice

# first prompt

```
Generate a complete Spring Boot microservice project implementing a User Service.

Requirements:

Architecture:
Use Hexagonal Architecture (Ports and Adapters).

Layers:

domain
application
adapters
infrastructure

Technology stack:

Java 17
Spring Boot
Gradle
Spring Data JPA
H2 in-memory database
JUnit 5
Mockito
Logback

Functional requirements:

Create user
Get user by id
Get all users

User fields:

UUID id
String name
String email
Instant createdAt

Architecture structure:

domain
  model
    User

application
  port
    UserRepositoryPort
  service
    UserService

adapters

  inbound
    rest
      UserController

  outbound
    persistence
      entity
        UserJpaEntity
      repository
        SpringDataUserRepository
      adapter
        UserPersistenceAdapter

infrastructure
  config
    LoggingInterceptor
    WebConfig

Features:

1. REST API endpoints:

POST /api/users
GET /api/users/{id}
GET /api/users

2. Persistence

Use Spring Data JPA
Use H2 in-memory database

3. Logging

Implement a generic request/response logging interceptor.

Log:

HTTP method
endpoint
request payload
response payload
execution time

The interceptor must apply to all endpoints automatically.

Use:

ContentCachingRequestWrapper
ContentCachingResponseWrapper

Register interceptor via WebMvcConfigurer.

4. Gradle build

Include dependencies:

spring-boot-starter-web
spring-boot-starter-data-jpa
h2
lombok
spring-boot-starter-test
mockito

5. Configuration

application.yml

Include:

H2 console enabled
JPA configuration
logging levels

6. Logging

Configure logback-spring.xml.

7. Tests

Implement unit tests for:

UserService
UserController
Domain entity

Use:

JUnit5
Mockito
MockMvc

8. Example Data

Insert sample users on startup using CommandLineRunner.

9. Output

Generate the entire project structure including:

build.gradle
settings.gradle
all source code
config files
tests

Ensure the project compiles and runs with:

./gradlew bootRun

```
