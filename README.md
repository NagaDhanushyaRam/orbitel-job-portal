# ORBITEL Job Portal System

A Spring Boot **monolith** using **Thymeleaf** for the web UI and **MySQL/H2** for persistence. Role‑based access for **Job Seeker**, **Employer**, and **Admin**.

## Tech Stack
- **Backend:** Java 17+, Spring Boot, Spring MVC, Spring Security, Spring Data JPA (Hibernate)
- **View layer:** Thymeleaf templates under `src/main/resources/templates`
- **Database:** MySQL (prod/dev) and H2 in‑memory (tests/dev)
- **Build:** Maven (wrapper included: `mvnw`, `mvnw.cmd`)

## Project Layout
```
orbitel/
├─ pom.xml
├─ src/main/java/com/orbitel/jobportal/
│  ├─ OrbitelApplication.java
│  ├─ config/            # SecurityConfig, etc.
│  ├─ controller/        # HomeController, AuthController, JobController, ...
│  ├─ dto/               # DTOs
│  ├─ entity/            # JPA entities: User, Role, Job, Application, Message, ...
│  ├─ repository/        # Spring Data repositories
│  └─ service/           # Service interfaces + impl
└─ src/main/resources/
   ├─ templates/         # Thymeleaf views (dashboard-*.html, jobs, profiles, auth, ...)
   ├─ static/            # CSS (e.g., styles.css)
   ├─ application.properties.example
   └─ application-h2.properties.example
```
> Note: Real configs `application.properties` and `application-h2.properties` are **ignored by Git**. Use the `.example` files to create your local copies.

## Features (from code)
- User registration & login (Spring Security)
- Role‑based dashboards (Admin/Employer/Seeker)
- CRUD for jobs, applications, messages, and profiles
- Server‑side rendered pages with Thymeleaf
- Repositories for Job, Application, User, Profiles, Messages

## Local Setup

### Prerequisites
- Java 17+
- MySQL 8+ (for MySQL profile) or none for H2
- Maven (wrapper included)

### 1) Create config from examples
Copy the examples and fill in credentials locally (do **not** commit the real files):
```
cp src/main/resources/application.properties.example src/main/resources/application.properties
cp src/main/resources/application-h2.properties.example src/main/resources/application-h2.properties
```

**MySQL example:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/orbitel?allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.profiles.active=mysql

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

**H2 example (in‑memory):**
```properties
spring.datasource.url=jdbc:h2:mem:orbitel;DB_CLOSE_DELAY=-1;MODE=MySQL
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### 2) Run
```bash
# from project root
./mvnw spring-boot:run
# Windows: mvnw.cmd spring-boot:run
```
App will start on `http://localhost:8080/`.

## Build & Test
```bash
./mvnw clean package
./mvnw test
```

## Deployment (basic)
- Set env vars or edit `application.properties` for prod DB.
- Package fat jar and run:
```bash
./mvnw clean package
java -jar target/*-SNAPSHOT.jar
```

## Security & Secrets
- Real configs are **gitignored**: keep passwords only locally.
- Commit only `*.example` config files.

## License
MIT. See `LICENSE`.
