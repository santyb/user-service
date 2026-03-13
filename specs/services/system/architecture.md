# Architecture

## Architectural Style

The service follows **Hexagonal Architecture (Ports and Adapters)**.

This architecture isolates business logic from external systems such as databases, APIs, or frameworks.

---

## Layers

### 1. Domain Layer

Contains core business logic.

Responsibilities:

- Domain entities
- Domain validation rules
- Domain exceptions

This layer must have **no dependencies on frameworks**.

---

### 2. Application Layer

Coordinates use cases.

Responsibilities:

- Application services
- Use case orchestration
- Ports (interfaces for repositories)

---

### 3. Infrastructure Layer

Implements technology-specific details.

Responsibilities:

- JPA entities
- Spring Data repositories
- Database configuration

---

### 4. Adapters

Adapters connect external systems.

Types:

Inbound adapters

- REST Controllers

Outbound adapters

- Database persistence adapter

---

## Dependency Rule

Dependencies must always point inward.

Adapters → Application → Domain

The Domain layer must never depend on Spring, JPA, or external frameworks.

---

## Benefits

- Testable business logic
- Framework independence
- Clear separation of concerns
- Replaceable infrastructure
