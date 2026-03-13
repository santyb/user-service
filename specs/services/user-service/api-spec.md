# API Specification

Base path:

/api/users

---

## 1 Create User

POST /api/users

### Request

```
{
"name": "John Doe",
"email": "john@example.com"
}
```

### Response

```
{
"id": "UUID",
"name": "John Doe",
"email": "john@example.com",
"createdAt": "2025-01-01T10:00:00Z"
}
```


---

## 2 Get User By Id

GET /api/users/{id}

### Response

```
{
"id": "UUID",
"name": "John Doe",
"email": "john@example.com",
"createdAt": "2025-01-01T10:00:00Z"
}
```

## 3 Get All Users

GET /api/users

### Response

```
[
  {
    "id": "UUID",
    "name": "John Doe",
    "email": "john@example.com",
    "createdAt": "2025-01-01T10:00:00Z"
  }
]
```