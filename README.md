# ORBITEL Job Portal System

A full‑stack job portal with role‑based access for Job Seekers, Employers, and Admins.

## Tech Stack
- **Backend:** Java, Spring Boot, Spring Security, JPA/Hibernate
- **Database:** MySQL
- **Frontend:** React (or Thymeleaf), HTML/CSS/JS
- **Build/Package:** Maven
- **Auth:** Role‑based (USER/EMPLOYER/ADMIN)

## Features
- User registration/login with encrypted passwords
- Role‑based dashboards (post jobs, apply to jobs, approve listings, admin moderation)
- CRUD for jobs, applications, and profiles
- Search, filter, pagination (where applicable)
- REST APIs for frontend integration

## Project Structure (example)
```
orbitel/
├─ backend/
│  ├─ src/main/java/... (Spring Boot app)
│  ├─ src/main/resources/
│  │  ├─ application.properties.example
│  │  └─ schema.sql / data.sql (optional)
│  └─ pom.xml or build.gradle
└─ frontend/
   ├─ src/
   ├─ package.json
   └─ vite.config.js or webpack config
```

> **Do not commit secrets**. Keep real credentials out of git. Use environment variables and commit only `application.properties.example`.

## Local Setup

### Prereqs
- Java 17+
- MySQL 8+
- Maven

### Configure DB
Copy `backend/src/main/resources/application.properties.example` to `application.properties` and update values:
```
spring.datasource.url=jdbc:mysql://localhost:3306/orbitel
spring.datasource.username=youruser
spring.datasource.password=yourpass
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```

### Run Backend
```bash
cd backend
./mvnw spring-boot:run    # or: ./gradlew bootRun
```

### Run Frontend
```bash
cd frontend
npm install
npm run dev
```

## Tests
```bash
cd backend
./mvnw test
```

## Deployment
- Create production DB and update env vars
- Package the backend: `./mvnw clean package` -> run jar
- Build frontend: `npm run build` -> serve static files (e.g., Nginx) or integrate with Spring

## License
MIT. See `LICENSE` for details.
