# Logging Specification

## Logging Framework

Logback via Spring Boot.

---

## Logging Goals

1. Log application events
2. Track API requests and responses
3. Measure execution time

---

## Request/Response Logging

Implement a **Spring HandlerInterceptor**.

Captured information:

- HTTP Method
- Endpoint
- Request body
- Response body
- Execution time

---

## Implementation Components

### LoggingInterceptor

Responsibilities:

- intercept requests
- store start time
- log request
- log response

---

### LoggingFilter / Wrapper

Use:

ContentCachingRequestWrapper  
ContentCachingResponseWrapper

To capture request and response payloads.

---

## Log Example
```
INFO Request: POST /api/users
Payload: {"name":"John","email":"john@test.com
"}

INFO Response: 201
Payload: {"id":"..."}
Execution time: 12ms
```


---

## Global Configuration

Interceptor registered via:

WebMvcConfigurer
