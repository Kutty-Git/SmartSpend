# SmartSpend

SmartSpend is a final-year college project for personal income, expense, category, budget, insight, notification, administration, and monthly PDF report management.

## Features
JWT login and registration use BCrypt hashes. Users have isolated expense, income, budget, and notification data. Admins manage users and categories and can open a selected user's dashboard in read-only mode. Dashboard statistics, deterministic rule-based insights, budget SAFE/WARNING/EXCEEDED status, audit-log viewing, and real OpenPDF monthly reports are included. No AI or external AI API is used.

## Stack and structure
Java 17+, Spring Boot 3.3, Spring Security, JWT, JPA/Hibernate, MySQL 8 or H2 fallback, Maven, and Vanilla HTML/CSS/JavaScript. `backend/src/main/java/com/smartspend` contains the domain, security, controllers, repositories, and report logic. `frontend` contains static pages and Fetch API client code. SQL and diagrams are under `database` and `docs`.

## Run
Install Java 17+ and Maven. Set `JWT_SECRET` to a secure value of at least 32 characters. From the extracted project root, run `cd SmartSpend/backend`, then `JWT_SECRET='replace-with-a-secure-secret-at-least-32-characters' mvn clean test` and `JWT_SECRET='replace-with-a-secure-secret-at-least-32-characters' mvn spring-boot:run`; H2 stores data under `backend/data`. Swagger is at `http://localhost:8080/swagger-ui/index.html`. In a second terminal, from the extracted project root, run `cd SmartSpend/frontend && python -m http.server 5500`, then open `http://localhost:5500`.

## MySQL
Create a database using `database/schema.sql`, export variables from `.env.example`, then start the backend. `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`, `JWT_EXPIRATION_HOURS`, and `CORS_ALLOWED_ORIGINS` are supported. Never use the demo secret or credentials in production.

## Demo account
Development-only admin: `admin@smartspend.local` / `Admin@123`. The seed configuration creates default categories and this account only when the database is empty.

## Main APIs
`/api/auth`, `/api/expenses`, `/api/incomes`, `/api/categories`, `/api/budgets`, `/api/dashboard`, `/api/insights`, `/api/notifications`, `/api/reports/monthly`, and ADMIN-only `/api/admin` (including selected-user read-only dashboard viewing). Send JWT as `Authorization: Bearer <token>`.

## PDF report
After login, Reports allows month/year selection and downloads `/api/reports/monthly?month=MM&year=YYYY` as a real `application/pdf` document containing user, totals, savings, and transactions.

## Testing and notes
Run `mvn clean test`. Tests cover BCrypt behavior, deterministic budget thresholds, and zero-income safety. The demo uses H2 by default; MySQL is the intended primary database. The frontend is intentionally dependency-free and uses browser Fetch API.


### Additional features
- Dashboard analytics charts (category breakdown, cash flow, six-month trend)
- Dark mode with saved preference
- Savings goals with progress tracking
- Expense calendar view
- Admin read-only user dashboard

## Render deployment copy

This deployment copy uses **PostgreSQL**, not H2, because hosted web-service filesystems should not be used as the persistent application database. See `RENDER-DEPLOYMENT.md` for the Render setup. The frontend backend URL is configured in `frontend/js/config.js`.

The original `SmartSpend-FINAL-Final.zip` should be kept unchanged as the local/demo backup.
