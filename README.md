# spring-boot-user-settings

A simple Spring Boot application for managing user settings with PostgreSQL integration, providing CRUD operations through RESTful APIs.

## Getting Started

### Prerequisites

- Docker
- Docker Compose
- Java 21
- Gradle

Clone the repository:
```
git clone https://github.com/SultanBadri/spring-boot-user-settings.git
```

Navigate to the project directory:
```
cd spring-boot-user-settings
```

Create a `.env` file in the project root with the following content:
```
POSTGRES_DB=your_db
POSTGRES_USER=your_username
POSTGRES_PASSWORD=your_password
```

Build the application JAR:
```
./gradlew build
```

Build and start the application with Docker Compose:
```
docker-compose up --build
```

The application will be running on http://localhost:8080.

## OpenAPI Documentation

This project includes OpenAPI documentation for the available RESTful APIs. Once the application is running, you can access the API documentation at http://localhost:8080/swagger-ui.html.

The OpenAPI documentation provides a user-friendly interface to interact with the API endpoints and view detailed information about request and response formats.