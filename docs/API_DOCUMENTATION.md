# API Documentation

Swagger UI: `/swagger-ui/index.html`; OpenAPI JSON: `/v3/api-docs`. Authenticate with `Authorization: Bearer <JWT>`.

| Area | Endpoints |
|---|---|
| Authentication | `POST /api/auth/register`, `POST /api/auth/login` |
| Expenses | `GET/POST /api/expenses`, `GET/PUT/DELETE /api/expenses/{id}` |
| Income | `GET/POST /api/incomes`, `GET/PUT/DELETE /api/incomes/{id}` |
| Categories | `GET /api/categories`; ADMIN `POST/PUT/DELETE` |
| Budgets | `GET/POST /api/budgets`, `GET/PUT/DELETE /api/budgets/{id}` |
| Dashboard | `GET /api/dashboard` |
| Insights | `GET /api/insights` |
| Notifications | `GET /api/notifications`, `PUT /api/notifications/{id}/read`, `PUT /api/notifications/read-all` |
| Reports | `GET /api/reports/monthly?month=MM&year=YYYY` returns `application/pdf` |
| Admin | ADMIN-only `/api/admin/users`, `/api/admin/stats`, `/api/admin/audit-logs`, `/api/admin/users/{id}/dashboard` (read-only user dashboard) |

Validation failures return 400. Missing resources return 404. Duplicate categories or email return 409. Invalid credentials return 401 and role restrictions return 403.
