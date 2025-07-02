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
- **Build Tool**: Gradle
- **Authentication**: JWT tokens

## Getting Started

### Prerequisites
- Java 17 or higher
- Node.js and npm
- Oracle Database
- Gradle

### Backend Setup
1. Clone the repository
2. Configure Oracle database connection in `application.properties`
3. Run `./gradlew bootRun`

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
