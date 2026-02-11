# SOS - Student Information System

SOS (Student Information System) is a modern Java web application built with Spring Boot that manages student information, enrollments, grades, loans, and payments.

## Technologies Used

- **Java 17+** - Programming language
- **Spring Boot 3.2.2** - Application framework
- **Spring MVC** - Web framework
- **Spring Data JPA** - Data persistence
- **Hibernate** - ORM (Object-Relational Mapping)
- **Jakarta Persistence** - Persistence API (for Spring Boot 3 compatibility)
- **HSQLDB** - In-memory database for development
- **Maven** - Dependency management and build tool
- **Bootstrap 5** - Frontend CSS framework
- **HTML/CSS/JavaScript** - Frontend technologies
- **BCrypt** - Password hashing

## Features

- **User Authentication**: Login and registration system with secure password hashing
- **Student Dashboard**: View personal information, enrollments, grades, loans, and payments
- **RESTful API**: Comprehensive API endpoints for all system operations
- **Responsive UI**: Mobile-friendly interface using Bootstrap

## Project Structure

```
src/
├── main/
│   ├── java/com/laa66/
│   │   ├── controller/     # REST and web controllers
│   │   ├── dao/           # Data Access Objects
│   │   ├── dao/impl/      # DAO implementations
│   │   ├── model/         # Entity models
│   │   ├── service/       # Business logic services
│   │   └── util/          # Utility classes
│   ├── resources/
│   │   ├── static/       # Static assets (HTML, CSS, JS)
│   │   ├── sql/          # Database initialization scripts
│   │   └── application.properties  # Configuration
│   └── webapp/           # Web resources (if any)
```

## Building the Application

### Prerequisites
- Java 17 or higher
- Maven 3.6.0 or higher

### Steps
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd sos
   ```

2. Build the application:
   ```bash
   mvn clean package
   ```

This will compile the code, run any tests, and create a JAR file in the `target/` directory.

## Running the Application

### Method 1: Using Maven (Development)
```bash
mvn spring-boot:run
```

### Method 2: Using Java (Production)
After building with `mvn package`, run:
```bash
java -jar target/sos-1.0-SNAPSHOT.jar
```

### Accessing the Application
Once the application is running, you can access it at:
- **Web Interface**: http://localhost:8080
- **API Documentation**: http://localhost:8080/api/info

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Students
- `GET /api/students/{id}` - Get student by ID
- `POST /api/students` - Create new student
- `PUT /api/students/{id}/lock` - Lock student account
- `PUT /api/students/{id}/activate` - Activate student account
- `GET /api/students/{id}/enrollments` - Get student enrollments
- `GET /api/students/{id}/grades` - Get student grades

### Loans
- `GET /api/loans/{id}` - Get loan by ID
- `POST /api/loans` - Create new loan
- `GET /api/loans/student/{studentId}` - Get loans for a student

### Payments
- `GET /api/payments/{id}` - Get payment by ID
- `POST /api/payments` - Create new payment
- `GET /api/payments/student/{studentId}/unpaid` - Get unpaid payments for a student

## Database Schema

The application uses an HSQLDB in-memory database with the following main entities:
- **Role**: User roles (STUDENT, ADMIN, LECTURER)
- **Student**: Student information and accounts
- **Course**: Academic courses
- **Enrollment**: Student enrollments in courses
- **Grade**: Academic grades
- **Loan**: Book loans
- **Payment**: Financial payments
- **Schedule**: Student schedules
- **ClassSession**: Individual class sessions
- **Notification**: System notifications

## Configuration

The application is configured through `src/main/resources/application.properties`:
- Database connection settings
- Server port (default: 8080)
- JPA/Hibernate settings
- Logging levels

## Design Patterns Used

- **DAO (Data Access Object)**: Separates business logic from data persistence
- **MVC (Model-View-Controller)**: Separates concerns in the web layer
- **Dependency Injection**: Managed by Spring framework
- **Repository Pattern**: Through Spring Data JPA
- **Service Layer**: Business logic encapsulation

## Development Notes

- The application uses in-memory HSQLDB for development and testing
- Passwords are securely hashed using BCrypt
- The frontend is built with Bootstrap for responsive design
- RESTful API design principles are followed
- Proper error handling and validation are implemented