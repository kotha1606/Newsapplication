# Repository Analysis: Phone Book Application

## Overview
This repository contains a **full-stack phone book application** that allows users to manage their personal contacts with authentication and CRUD operations.

## Architecture & Technology Stack

### Backend (Spring Boot)
- **Framework**: Spring Boot 3.2.1 with Java 17
- **Security**: Spring Security with JWT authentication
- **Database**: Oracle Database with Spring Data JPA
- **Build Tool**: Gradle
- **Key Dependencies**:
  - Spring Boot Starter Web (REST API)
  - Spring Boot Starter Security (Authentication)
  - Spring Boot Starter Data JPA (Database operations)
  - Oracle JDBC Driver
  - JWT libraries (jjwt)
  - Lombok (Code generation)

### Frontend (Angular)
- **Framework**: Angular 20.x (latest version)
- **UI Library**: Angular Material
- **Build Tool**: Angular CLI
- **Key Features**:
  - Modern responsive UI
  - Component-based architecture
  - Service-based API integration

### Database
- **Type**: Oracle Database
- **ORM**: Spring Data JPA with Hibernate

## Core Functionality

### 1. User Management
- **User Registration**: New users can create accounts
- **User Authentication**: JWT-based login system
- **Role-based Security**: User roles and permissions

### 2. Contact Management
- **Create Contacts**: Add new contacts with details
- **Read Contacts**: View personal contact list
- **Update Contacts**: Edit existing contact information
- **Delete Contacts**: Remove unwanted contacts
- **User-specific**: Each user can only access their own contacts

### 3. API Endpoints
The backend provides RESTful APIs:
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login  
- `GET /api/contacts` - Retrieve user's contacts
- `POST /api/contacts` - Create new contact
- `PUT /api/contacts/{id}` - Update existing contact
- `DELETE /api/contacts/{id}` - Delete contact

## Project Structure

### Backend Structure (`src/main/java/com/phonebook/`)
- `PhonebookApplication.java` - Main Spring Boot application
- `controller/` - REST API controllers (AuthController, ContactController)
- `entity/` - JPA entities (User, Contact, Role)
- `service/` - Business logic layer
- `repository/` - Data access layer
- `dto/` - Data transfer objects
- `config/` - Security and application configuration

### Frontend Structure (`frontend/src/app/`)
- `components/` - Angular components (login, register, contact-list, contact-form, navbar)
- `services/` - API service classes (auth.service, contact.service)
- `models/` - TypeScript interfaces/models
- `guards/` - Route guards for authentication
- `app.routes.ts` - Application routing configuration

## Key Features

1. **Secure Authentication**: JWT-based authentication system
2. **User Isolation**: Each user can only manage their own contacts
3. **Modern UI**: Angular Material-based responsive interface
4. **RESTful API**: Clean REST API design following best practices
5. **Database Integration**: Robust Oracle database integration
6. **Full CRUD Operations**: Complete create, read, update, delete functionality
7. **Validation**: Both frontend and backend data validation

## Development Setup
- **Backend**: Requires Java 17+, Oracle Database, runs on Spring Boot
- **Frontend**: Requires Node.js/npm, runs on Angular CLI
- **Build**: Gradle for backend, npm/Angular CLI for frontend

## Purpose
This application serves as a complete example of a modern full-stack web application for contact management, demonstrating:
- Enterprise-level Spring Boot backend architecture
- Modern Angular frontend development
- JWT authentication implementation
- Oracle database integration
- RESTful API design
- Security best practices

The application could be used as a personal contact manager or as a foundation for more complex contact management systems.