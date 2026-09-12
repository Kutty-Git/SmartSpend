# Database Design

The application uses one enum role model (`USER`, `ADMIN`) and one enum payment model (`CASH`, `CARD`, `UPI`, `NET_BANKING`, `WALLET`). `users.email` and `categories.name` are unique. Finance records reference their owner with many-to-one relationships; expenses and budgets optionally reference a category. Hibernate creates the aligned H2/MySQL tables from these entities. `database/schema.sql` creates the MySQL database, while Spring JPA creates the tables. Passwords are BCrypt hashes only.
