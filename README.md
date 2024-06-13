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

Build the application JAR without tests:
```
./gradlew build -x test
```

Build and start the application with Docker Compose:
```
docker-compose up --build
```

The application will be running on http://localhost:8080.
