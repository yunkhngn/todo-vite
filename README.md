# TodoVite

A full-stack Todo application built with **Spring Boot** (Backend) and **Vite + React** (Frontend), using **PostgreSQL** as the database.

## Tech Stack

- **Backend**: Spring Boot, Spring Data JPA, Hibernate
- **Frontend**: React, TypeScript, Vite
- **Database**: PostgreSQL
- **Infrastructure**: Docker & Docker Compose

## Quick Start

### Development
To run the application in development mode with hot-reloading for the frontend:

```bash
docker compose up --build
```

- **Frontend**: http://localhost:5173
- **Backend**: http://localhost:8080
- **Database**: Port 5432

### Production
To accept the production build (Vite build served via Nginx):

```bash
docker compose -f docker-compose.prod.yml up --build
```

- **Application**: http://localhost
