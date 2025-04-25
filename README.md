# House Of MarkTech Backend Assessment
this is a backend development  task submitted for  House of MarkTech. The project is a Restful API built using **spring Boot** and **H2 database**, providing token based authentication and task management features.

## Features
- **User Registration**
- **User Login**
- **Token based authentication**
- **Task Management Features**
  - User can ADD Task
  - User can View Task
  - User Can Update Task
  - User Can Delete Task
- **In-Memory Storage using H2-Database**

## Technologies Used
- Java
- SpringBoot
- Spring Security
- H2 Database
- Maven build
## APIs EndPoints
### Auth
#### 1. Register User
- Method: **POST** `/Auth/register`
- Description: `Register a new user`
#### 2. Login User
- Method: **POST** `/Auth/login`
- Description: `Logs in a new user and Generate a token`

### Tasks (AUTHENTICATED ENDPOINTS)
#### 1. Adding task 
- Method: **POST** `/userTask/add`
- Description: `Adds a new task `
#### 2. View All Tasks
- Method: **GET** `/userTask`
- Description: `User can view all tasks he added`
#### 3. Update a Task
- Method: **PUT** `/userTask/id`
- Description: `user can update any task he/she created`
#### 4. Delete a Task
- Method: **DELETE** `/userTask/id`
- Description: `User can delete task by its id`

## H2 Console
- URL: `http://localhost:8080/h2-console/`
- JDBC URL: `jdbc:h2:mem:taskdb`
- User: `sa`
- Password: ` `

## How to Run
 1. **Clone the repository**
    ```bash
      git clone  https://github.com/jaquen20/BackendAssessmentHOM.git
    ```
 2. **Navigate to the Project directory**
    ```bash 
      cd BackendAssessmentHOM 
    ```
    
3. **Build and Run th spring boot application**
 - if you are using maven: 
    ``` bash
      mvn spring-boot:run 
   ```
 - if you're using an IDE like IntelliJ or Eclipse:
   - open the project
   - Run the `main()` method from the main class
 
4. **Access the H2 Database Console**
- URL:`http://localhost/8080/h2-console`
- JDBC URL: `jdbc:h2:mem:taskdb`
- username: `sa`
- password: ` `
5. **Test the API using Postman**
- Use the `/auth/register` and `/auth/login` endpoints to  create and authenticate users
- Use the token to access protected endpoints

## Author

**Sandeep Kumar Verma**
GitHub: [@jaquen20](https://github.com/jaquen20)
