# Domain Model

## Entity: User

Represents a system user.

---

## Fields

| Field | Type | Required | Description |
|------|------|----------|-------------|
| id | UUID | yes | Unique identifier |
| name | String | yes | User name |
| email | String | yes | Email address |
| createdAt | Instant | yes | Creation timestamp |

---

## Constraints

Name:

- must not be empty

Email:

- must be valid format
- must not be empty

CreatedAt:

- automatically generated

---

## Domain Rules

1. A user must have a name
2. A user must have a valid email
3. User ID is generated automatically

---

## Domain Entity Example

```
User
├─ UUID id
├─ String name
├─ String email
└─ Instant createdAt
```