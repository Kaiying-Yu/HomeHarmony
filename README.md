# HomeHarmony

HomeHarmony is a web application that helps households manage and track chores among members. Users can create spaces, join existing spaces, assign chores, and earn points for completing tasks.

## Features

- User authentication
- Space creation and management
- Chore assignment and tracking
- Points system for completed chores
- Real-time updates of chore status

## Demo
<iframe width="560" height="315" src="https://www.youtube.com/embed/4XC9cezI4PI?si=4aFz9ANEKH2oY8eb" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>

## Tech Stack

- Frontend: Vue.js 2.x with Element UI
- Backend: Spring Boot 3.x
- Database: MySQL
- Build Tools: Maven (backend), npm (frontend)

## Prerequisites

- Node.js (v12 or higher)
- Java JDK 21
- MySQL 8.0
- Maven 3.x

## Setup Instructions

### 1. Database Setup

1. Install MySQL 8.0
2. Create a new database:
```sql
CREATE DATABASE dbhh;
```
3. Use the following credentials (as configured in application.properties):
```
Username: root
Password: 1234
```

### 2. Backend Setup

1. Navigate to the backend directory:
```bash
cd HH-Backend-SpringBoot
```
2. Install dependencies and build:
```bash
mvn clean install
```
3. Run the application:
```bash
mvn spring-boot:run
```
The backend server will start on `http://localhost:8080`

### 3. Frontend Setup

1. Navigate to the frontend directory:
```bash
cd hh-vue-project
```
2. Install dependencies:
```bash
npm install
```
3. Start the development server:
```bash
npm run serve
```
The frontend application will be available at `http://localhost:7000`

## Usage

1. Access the application at `http://localhost:7000`
2. Login with the following credentials:
```
Username: Cici
Password: 147258
```
3. After logging in, you can:
   - Create a new space or join an existing one
   - View and manage chores
   - Track points earned by space members
   - Assign chores to members
     
## Project Structure

### Frontend
The Vue.js frontend is organized with the following main routes:
- `/login` - Authentication view
- `/space` - Space management
- `/chores` - Chore management
- `/user` - User profile and settings

### Backend
The Spring Boot backend follows a standard layered architecture:
- Controllers: Handle HTTP requests
- Services: Implement business logic
- Mappers: Handle database operations
- Models: Define data structures

## Configuration Files

### Backend Configuration (application.properties)
```properties
spring.application.name=homeharmony
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/dbhh
spring.datasource.username=root
spring.datasource.password=1234
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
mybatis.configuration.map-underscore-to-camel-case=true
```

### Frontend Configuration (vue.config.js)
```javascript
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    port: 7000,
    open: true
  }
})
```

## Troubleshooting

1. If you encounter database connection issues:
   - Verify MySQL is running
   - Check database credentials in application.properties
   - Ensure database 'dbhh' exists

2. If the frontend fails to connect to the backend:
   - Verify backend is running on port 8080
   - Check CORS configuration in WebConfig.java
   - Ensure API endpoints are correctly configured in frontend services

3. For login issues:
   - Clear browser localStorage
   - Verify credentials
   - Check backend logs for authentication errors

