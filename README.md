# Phone Book Application

A full-stack phone book application built with Spring Boot backend, Angular frontend, and Oracle database.

## Features

- **Backend**: Spring Boot with Spring Security
- **Frontend**: Angular with modern UI
- **Database**: Oracle Database
- **Authentication**: JWT-based security
- **Contact Management**: Create, read, update, delete contacts
- **User Management**: User registration and authentication

## Technology Stack

- **Backend**: Spring Boot 3.2.1, Spring Security, Spring Data JPA
- **Frontend**: Angular (latest version)
- **Database**: Oracle Database
- **Build Tool**: Maven
- **Authentication**: JWT tokens

## Getting Started

### Prerequisites
- Java 17 or higher
- Node.js and npm
- Oracle Database
- Maven (or use the included Maven wrapper)

### Backend Setup
1. Clone the repository
2. Configure Oracle database connection in `application.properties`
3. Run `./mvnw spring-boot:run` (Unix/Linux/Mac) or `mvnw.cmd spring-boot:run` (Windows)

### Frontend Setup
1. Navigate to the frontend directory
2. Run `npm install`
3. Run `ng serve`

## API Endpoints

- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login
- `GET /api/contacts` - Get user's contacts
- `POST /api/contacts` - Create new contact
- `PUT /api/contacts/{id}` - Update contact
- `DELETE /api/contacts/{id}` - Delete contact

## Maven Commands

- `./mvnw clean compile` - Clean and compile the project
- `./mvnw test` - Run tests
- `./mvnw spring-boot:run` - Run the application
- `./mvnw package` - Package the application as JAR
- `./mvnw clean install` - Clean, compile, test, and install

## Project Structure

```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/phonebook/
│   │   │       ├── PhonebookApplication.java
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── entity/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   └── test/
├── frontend/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```
