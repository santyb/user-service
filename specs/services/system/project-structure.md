# Project Structure

Hexagonal Architecture folder layout.

### src/main/java/com/example/userservice

```
domain
  model
      User.java

application
  port
      UserRepositoryPort.java
  service
      UserService.java

adapters
  inbound
      rest
          UserController.java
  outbound
      persistence
          entity
              UserJpaEntity.java
          repository
              SpringDataUserRepository.java
          adapter
              UserPersistenceAdapter.java

infrastructure
  config
      LoggingInterceptor.java
      WebConfig.java
```

### src/main/resources

```
application.yml
logback-spring.xml
```
### src/test/java
```
domain
application
adapters/rest
```
