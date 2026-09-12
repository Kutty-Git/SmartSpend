# SmartSpend – Render Deployment Guide

This copy is prepared for online deployment with **Render + Spring Boot + PostgreSQL**.
The original `SmartSpend-FINAL-Final.zip` remains the safe local/H2 backup.

## 1. Create PostgreSQL on Render

Create a PostgreSQL database in Render. From the database details, copy the connection values needed by the backend:

- Host
- Port (normally 5432)
- Database name
- Username
- Password

Build `DB_URL` as:

`jdbc:postgresql://HOST:5432/DATABASE`

Use the database's internal/private connection details when the backend and database are both on Render.

## 2. Deploy the Spring Boot backend

Create a Render Web Service from this repository/project and select the `backend` directory as the root directory if your repository contains both frontend and backend.

Recommended settings:

- Runtime: Java
- Build Command: `mvn clean package -DskipTests`
- Start Command: `java -jar target/smartspend-backend-1.0.0.jar`

Render provides the `PORT` variable; the application reads it automatically.

### Backend environment variables

Set these in Render:

- `DB_URL` = `jdbc:postgresql://HOST:5432/DATABASE`
- `DB_USERNAME` = PostgreSQL username
- `DB_PASSWORD` = PostgreSQL password
- `JWT_SECRET` = a secure random string of at least 32 characters
- `JWT_EXPIRATION_HOURS` = `24`
- `CORS_ALLOWED_ORIGINS` = the final frontend Render URL, for example `https://your-frontend.onrender.com`

Do **not** commit real passwords or JWT secrets to GitHub.

## 3. Deploy the frontend

Create a Render Static Site using the `frontend` folder.

Before deploying, edit:

`frontend/js/config.js`

Change:

`window.SMARTSPEND_API_BASE = 'https://YOUR-BACKEND-SERVICE.onrender.com/api';`

to the real backend URL.

No Node.js build is required because the frontend is plain HTML/CSS/JavaScript.

## 4. Update CORS

After the frontend receives its final Render URL, make sure the backend's `CORS_ALLOWED_ORIGINS` exactly matches that URL.

Example:

`https://smartspend-frontend.onrender.com`

Redeploy/restart the backend after changing the variable.

## 5. First login

`SeedConfig` creates the demo admin only when the user table is empty:

- Email: `admin@smartspend.local`
- Password: `Admin@123`

Change/remove demo credentials before treating the deployment as a real production application.

## 6. Important database note

The deployment build does **not** use the local H2 file database. PostgreSQL is required for this hosted version so data persists outside the web service filesystem.

## 7. Local development

The deployment copy is intended for PostgreSQL. For the untouched local/demo version, continue using `SmartSpend-FINAL-Final.zip` and its existing H2 configuration.
