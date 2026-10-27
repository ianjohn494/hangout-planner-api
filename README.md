
# Hangout Planner API

The backend service for **Hangout Planner**, an application designed to help friends organize activities and plan hangouts together.

Built with **Java, Spring Boot, and MongoDB**, this REST API provides endpoints for retrieving and managing activity data.

## Features

- **Activity Management:** Retrieve activities, including names, categories, and estimated costs.
- **REST API:** Provide HTTP endpoints for frontend communication.
- **MongoDB Integration:** Store and retrieve activity data.
- **Health Check:** Verify that the backend application is running.
- **Layered Architecture:** Separate controllers, services, repositories, and data models.

## Technology Stack

- Java
- Spring Boot
- Spring Web
- Spring Data MongoDB
- MongoDB
- Maven
- Git and GitHub

## API Endpoints

### Health Check

Checks whether the API is running and responding to requests.

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/health/api/health` | Returns the service status and name |

**Example request:**

```bash
curl -i http://localhost:8080/health/api/health
```

**Example response:**

```json
{
  "status": "UP",
  "service": "hangout-planner-api"
}
```

### Activities

Retrieves all activities stored in MongoDB.

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/activities` | Retrieves all activities |

**Example request:**

```bash
curl -i http://localhost:8080/api/activities
```

The response is a JSON array of activity objects containing fields such as the activity name, category, and estimated cost.

## Getting Started

### Prerequisites

Make sure you have the following installed or configured:

- Java compatible with the project configuration
- Maven or the Maven Wrapper
- MongoDB
- Git

### 1. Clone the Repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd hangout-planner-api
```

Replace `<YOUR_GITHUB_REPOSITORY_URL>` with your repository's URL.

### 2. Configure MongoDB

Configure your MongoDB connection in `src/main/resources/application.properties`.

Example for a local MongoDB instance:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/hangout_planner
```

Use the connection details appropriate for your environment. Never commit database passwords or other secrets to GitHub.

### 3. Run the Application

Using the Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Alternatively, if Maven is installed:

```bash
mvn spring-boot:run
```

The application typically runs at:

`http://localhost:8080`

### 4. Test the API

Check the health endpoint:

```bash
curl -i http://localhost:8080/health/api/health
```

Retrieve all activities:

```bash
curl -i http://localhost:8080/api/activities
```

The activities endpoint requires a working MongoDB connection.

## Frontend Integration

The API is designed to work with the separate **Hangout Planner UI** repository, built with React and TypeScript.

During local development:

- **Frontend:** `http://localhost:5173`
- **Backend:** `http://localhost:8080`

The backend must allow requests from the frontend's origin through its Cross-Origin Resource Sharing (CORS) configuration.

## Project Architecture

The API uses a layered architecture:

- **Controller:** Handles incoming HTTP requests.
- **Service:** Contains business logic.
- **Repository:** Handles database operations.
- **Model:** Represents activity data.
- **Health Check:** Reports basic application availability.

## Future Improvements

- Add endpoints to create, update, and delete activities.
- Implement service-layer business logic and validation.
- Add activity filtering and search.
- Support hangouts, groups, invitations, and availability.
- Add automated unit and integration tests.
- Improve error handling and request validation.
- Document endpoints using OpenAPI / Swagger.
- Containerize the application and database with Docker.
- Complete frontend and backend integration.

## Contributing

Contributions should follow a collaborative Git workflow:

1. Create a feature branch.
2. Implement and test changes.
3. Commit with a descriptive message.
4. Open a pull request for review.

## License

No license has been specified yet.
