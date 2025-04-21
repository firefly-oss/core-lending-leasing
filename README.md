# Core Lending Leasing Service

## Overview
The Core Lending Leasing Service is a microservice component of the Firefly platform that manages leasing agreements, assets, payment records, installment schedules, and other leasing-related functionality. It provides a comprehensive API for creating and managing leasing agreements and their associated data.

## Architecture

### Module Structure
The service is built as a multi-module Maven project with the following components:

1. **core-lending-leasing-interfaces**: Contains DTOs (Data Transfer Objects) and API interfaces
2. **core-lending-leasing-models**: Contains database entities and repositories
3. **core-lending-leasing-core**: Contains business logic and service implementations
4. **core-lending-leasing-web**: Contains REST controllers and application configuration

### Technology Stack
- **Java 21**: Programming language
- **Spring Boot**: Application framework
- **Spring WebFlux**: Reactive web framework
- **R2DBC**: Reactive database connectivity
- **PostgreSQL**: Database
- **Flyway**: Database migration
- **Maven**: Build tool
- **Docker**: Containerization

## Setup and Installation

### Prerequisites
- Java 21 or higher
- Maven 3.8 or higher
- Docker (for containerized deployment)
- PostgreSQL database

### Environment Variables
The following environment variables need to be set:

```
DB_HOST=localhost
DB_PORT=5432
DB_NAME=lending_leasing
DB_USERNAME=postgres
DB_PASSWORD=postgres
DB_SSL_MODE=disable
```

### Building the Application
```bash
mvn clean package
```

### Running Locally
```bash
mvn spring-boot:run -pl core-lending-leasing-web
```

### Running with Docker
```bash
# Build the Docker image
docker build -t core-lending-leasing .

# Run the container
docker run -p 8080:8080 \
  -e DB_HOST=host.docker.internal \
  -e DB_PORT=5432 \
  -e DB_NAME=lending_leasing \
  -e DB_USERNAME=postgres \
  -e DB_PASSWORD=postgres \
  -e DB_SSL_MODE=disable \
  core-lending-leasing
```

## API Documentation

The service provides a RESTful API with the following main endpoints:

### Leasing Agreements
- `GET /api/v1/leasing-agreements`: List or search leasing agreements
- `POST /api/v1/leasing-agreements`: Create a new leasing agreement
- `GET /api/v1/leasing-agreements/{id}`: Get a leasing agreement by ID
- `PUT /api/v1/leasing-agreements/{id}`: Update a leasing agreement
- `DELETE /api/v1/leasing-agreements/{id}`: Delete a leasing agreement

### Leasing Assets
- `GET /api/v1/leasing-assets`: List or search leasing assets
- `POST /api/v1/leasing-assets`: Create a new leasing asset
- `GET /api/v1/leasing-assets/{id}`: Get a leasing asset by ID
- `PUT /api/v1/leasing-assets/{id}`: Update a leasing asset
- `DELETE /api/v1/leasing-assets/{id}`: Delete a leasing asset

### Lease Payment Records
- `GET /api/v1/lease-payment-records`: List or search lease payment records
- `POST /api/v1/lease-payment-records`: Create a new lease payment record
- `GET /api/v1/lease-payment-records/{id}`: Get a lease payment record by ID
- `PUT /api/v1/lease-payment-records/{id}`: Update a lease payment record
- `DELETE /api/v1/lease-payment-records/{id}`: Delete a lease payment record

### Lease Installment Schedules
- `GET /api/v1/lease-installment-schedules`: List or search lease installment schedules
- `POST /api/v1/lease-installment-schedules`: Create a new lease installment schedule
- `GET /api/v1/lease-installment-schedules/{id}`: Get a lease installment schedule by ID
- `PUT /api/v1/lease-installment-schedules/{id}`: Update a lease installment schedule
- `DELETE /api/v1/lease-installment-schedules/{id}`: Delete a lease installment schedule

### Lease End Options
- `GET /api/v1/lease-end-options`: List or search lease end options
- `POST /api/v1/lease-end-options`: Create a new lease end option
- `GET /api/v1/lease-end-options/{id}`: Get a lease end option by ID
- `PUT /api/v1/lease-end-options/{id}`: Update a lease end option
- `DELETE /api/v1/lease-end-options/{id}`: Delete a lease end option

### Lease Service Events
- `GET /api/v1/lease-service-events`: List or search lease service events
- `POST /api/v1/lease-service-events`: Create a new lease service event
- `GET /api/v1/lease-service-events/{id}`: Get a lease service event by ID
- `PUT /api/v1/lease-service-events/{id}`: Update a lease service event
- `DELETE /api/v1/lease-service-events/{id}`: Delete a lease service event

### Swagger/OpenAPI Documentation
The API documentation is available at:
- `/swagger-ui.html`: Swagger UI
- `/v3/api-docs`: OpenAPI specification

## Configuration

### Application Profiles
The application supports the following profiles:
- **dev**: Development environment with detailed logging
- **testing**: Testing environment
- **prod**: Production environment with minimal logging and disabled Swagger UI

### Database Configuration
The application uses PostgreSQL with R2DBC for reactive database access. Flyway is used for database migrations.

## Development Guidelines

### Project Structure
- Follow the package structure: `com.catalis.core.lending.leasing.[module].[component].[version]`
- Use versioning in package names (e.g., `v1`) to support API versioning
- Keep controllers in the web module
- Keep business logic in the core module
- Keep data access in the models module
- Keep DTOs and interfaces in the interfaces module

### Coding Standards
- Use reactive programming with Reactor (Mono/Flux)
- Follow Spring Boot best practices
- Use proper error handling with Mono.error()
- Implement proper validation
- Write unit tests for all components

## Monitoring and Health Checks

The application provides the following endpoints for monitoring:
- `/actuator/health`: Health check endpoint
- `/actuator/info`: Application information
- `/actuator/prometheus`: Prometheus metrics

## Deployment

### Kubernetes
The application can be deployed to Kubernetes using the provided Dockerfile. Make sure to set the required environment variables in your Kubernetes deployment configuration.

### CI/CD
The application uses GitHub Actions for CI/CD. The workflow is defined in `.github/workflows/publish.yml`.