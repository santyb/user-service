# Testing Specification

## Testing Frameworks

- JUnit 5
- Mockito
- Spring Boot Test

---

## Test Types

### 1 Domain Tests

Test:

- entity creation
- validation logic

---

### 2 Application Service Tests

Test:

- use case execution
- repository interactions

Mock:

- repository ports

---

### 3 Controller Tests

Use:

MockMvc

Test:

- API endpoints
- request validation
- response status

---

## Coverage Target

Minimum:

80% code coverage

Focus areas:

- application services
- controllers

---

## Test Naming

Example:

UserServiceTest  
UserControllerTest  
UserDomainTest
