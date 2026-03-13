# Use Cases

---

## UC1 — Create User

Actor: API Client

Flow:

1. Client sends POST /users
2. Controller validates request
3. Application service executes create user
4. Domain entity is created
5. Repository saves entity
6. Response returned

---

## UC2 — Get User By ID

Actor: API Client

Flow:

1. Client calls GET /users/{id}
2. Application service retrieves user
3. Repository fetches entity
4. Result returned

If user not found → return 404

---

## UC3 — List Users

Actor: API Client

Flow:

1. Client calls GET /users
2. Application service retrieves all users
3. Repository fetches data
4. Response returned
