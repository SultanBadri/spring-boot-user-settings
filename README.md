# spring-boot-user-settings

A simple Spring Boot application for managing user settings with PostgreSQL integration, providing CRUD operations through RESTful APIs.

### Getting Started

Clone the repository:
```
git clone https://github.com/SultanBadri/spring-boot-user-settings.git
```

Navigate to the project directory:
```
cd spring-boot-user-settings
```

Set up a PostgreSQL database:
- Install PostgreSQL if you haven't already.
- Create a new database named `user_settings_db`.
  - If you decide to use a different name, update the database URL in `application.properties`.
- Optionally, you can use a `.env` file in the root of the project to store the database username and password.

Build the application:
```
./gradlew bootRun
```

The application is running on http://localhost:8080.
