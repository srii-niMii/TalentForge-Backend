# TalentForge Backend

TalentForge Backend is a Java-based enterprise application built with Spring Boot to support talent acquisition and recruitment workflows. It powers core business functions such as candidate management, job posting, authentication, user access control, and document/media handling.

## Overview

This backend service provides a secure and scalable foundation for a recruitment platform, enabling:
- candidate onboarding and lifecycle tracking,
- job publishing and retrieval,
- authorization and session management,
- integration with cloud-based media storage,
- API documentation for efficient developer workflows.

The application follows a layered architecture based on Spring Boot conventions and Java enterprise patterns, with clear separation between controllers, services, repositories, entities, DTOs, and configuration components.

## Tech Stack

- Java 17
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data JPA
- Spring Security
- PostgreSQL
- JWT (JJWT)
- Cloudinary
- Springdoc OpenAPI / Swagger
- Maven

## Architecture

The project is organized around a standard Spring Boot structure:

```text
src/
├── main/
│   └── java/
│       └── TalentForge/
│           ├── config/
│           ├── controller/
│           ├── dto/
│           ├── entity/
│           ├── enums/
│           ├── exception/
│           ├── repository/
│           ├── security/
│           ├── service/
│           └── TalentForgeApplication.java
├── test/
│   └── java/
└── resources/
```

### Core packages
- `config` — application configuration, JWT properties, OpenAPI, and external integrations
- `controller` — REST endpoints for public and protected operations
- `service` — business logic and orchestration
- `repository` — persistence layer for database interactions
- `entity` — domain models
- `dto` — data transfer objects for API communication
- `exception` — custom error handling and validation patterns
- `security` — authentication and authorization support

## Features

- Secure authentication and authorization
- Candidate management endpoints
- Job listing and management APIs
- Public access for job discovery
- Admin operations
- JWT-based stateless authentication
- Media upload support via Cloudinary
- Swagger/OpenAPI API documentation
- PostgreSQL-backed persistence

## Prerequisites

Before running this project, ensure the following are installed and configured:

- Java 17+
- Maven 3.9+
- PostgreSQL
- A configured database instance
- Environment variables or application configuration for:
  - database credentials
  - JWT secret and token settings
  - Cloudinary credentials


## Getting Started

### Clone the repository

```bash
git clone 
cd TalentForge-Backend
```

### Run the application

```bash
./mvnw spring-boot:run
```

For Windows:

```bash
mvnw.cmd spring-boot:run
```

### Build the project

```bash
./mvnw clean package
```

## API Documentation

This project includes Springdoc OpenAPI support, making the backend APIs visible through Swagger UI.

Once the application is running, access:

```text
http://localhost:8080/swagger-ui/index.html
```

This enables interactive testing and faster integration with frontend services.

## Database

The application is configured to use PostgreSQL and Spring Data JPA for persistence. Ensure that your database server is running and connection settings are correctly defined before starting the application.

## Testing

The project includes test dependencies for Spring Boot and security layers. You can run the test suite using:

```bash
./mvnw test
```
