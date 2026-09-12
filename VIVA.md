# SmartSpend Viva Notes

**What is SmartSpend?** A secure finance app for tracking income, expenses, budgets, insights, notifications, and PDF reports.

**Why Spring Boot?** It provides embedded web-server support, dependency injection, REST controllers, validation, and easy database integration.

**Spring Security, authentication, authorization:** Security protects endpoints. Authentication verifies identity; authorization decides whether that identity may perform an action.

**JWT:** A signed token returned after login and sent with later requests. It avoids storing a server session for this project.

**BCrypt:** A one-way adaptive password hash. The raw password is never stored.

**JPA, Hibernate, Spring Data JPA:** JPA is the object-relational standard; Hibernate implements it; Spring Data creates repository implementations from interfaces.

**REST, DTO, CRUD:** REST exposes resources over HTTP. Request/response maps prevent direct entity exposure. CRUD means create, read, update, and delete.

**USER versus ADMIN:** Users manage only their own finance records. Admins manage users, categories, statistics, and audit logs.

**MySQL and H2:** MySQL is the primary database. H2 is a convenient local fallback for demonstrations.

**Budget status:** Used divided by budget times 100. Under 80% is SAFE, 80–99.99% is WARNING, and 100% or above is EXCEEDED.

**Dashboard:** Income minus expense gives balance and savings. Savings percentage is savings divided by income; zero income is handled safely.

**Rule-based insights:** Transparent rules flag category spending above 30%, budget warning/exceeded states, and monthly spending changes. No AI is used.

**PDF report:** OpenPDF creates a downloadable monthly PDF containing user information, totals, savings, and transaction lines.

**Audit logging and exceptions:** AuditLog stores important activity metadata. Global exception handling returns consistent status, error, message, and timestamp JSON.

**Testing:** Maven runs JUnit tests for BCrypt, budget threshold logic, and zero-income behavior. The application can be exercised through Swagger and the Vanilla JS UI.
