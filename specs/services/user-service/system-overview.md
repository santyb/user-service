# User Service — System Overview

## Purpose

The User Service is a Spring Boot microservice responsible for managing user information.  
It provides REST APIs to create users and retrieve user information.

The service persists user data in an H2 in-memory database and follows **Hexagonal Architecture (Ports & Adapters)** to maintain strong separation of concerns and testability.

---

## Key Features

The service supports the following operations:

1. Create a user
2. Retrieve a user by ID
3. Retrieve all users

---

## User Fields

| Field | Type | Description |
|-----|-----|-------------|
| id | UUID | Unique identifier |
| name | String | User name |
| email | String | User email |
| createdAt | Instant | Creation timestamp |

---

## Technology Stack

| Component | Technology       |
|--------|------------------|
| Language | Java 21           |
| Framework | Spring Boot      |
| Build Tool | Gradle           |
| Database | H2 (in-memory)   |
| Persistence | Spring Data JPA  |
| Testing | JUnit5 + Mockito |
| Logging | Logback          |

---

## Architecture

The service follows **Hexagonal Architecture** with the following layers:

Core:

- Domain
- Application

External adapters:

- REST API adapter
- Persistence adapter
- Logging interceptor

---

## High-Level Flow

Client → REST Controller → Application Service → Domain → Repository Port → Persistence Adapter → Database
