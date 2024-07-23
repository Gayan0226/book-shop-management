# Bookshop Management System

This repository contains the backend for a bookshop application. It is built using Spring Boot and integrates with a MySQL database. The application provides various endpoints for managing authors, books, and user reactions.

## Features

- **CRUD Operations**: Manage authors and books.
- **Search and Categorization**: Search books by ISBN and categorize them.
- **User Reactions**:count user reactions (likes/dislikes).
- **Periodic Tasks**: Generate reports and logs at regular intervals,an email notification should be generated every 
5 minute's containing the information of like count for each author..
- **Logging**: Every event in the system is logged.

## Endpoints

### Author

- **Register a new author**: `POST /api/v1/author-controller/add-author`
- **Update an existing author**: `PUT /api/v1/author-controller/author-update-by-id`
- **Update author name and contacts**: `PUT /api/v1/author-controller/author-name-contacts-update`
- **Delete an author by ID**: `DELETE /api/v1/author-controller/delete-author-by-id`
- **Get author by ID**: `GET /api/v1/author-controller/get-author-by-id`
- **Get all authors**: `GET /api/v1/author-controller/get-authors`

### Book

- **Register a new book**: `POST /api/v1/book-controller/add-book`
- **Update book details**: `PUT /api/v1/book-controller/update-book-details`
- **Get books by category**: `GET /api/v1/book-controller/books-name-page-by-category`
- **Delete a book by ID**: `DELETE /api/v1/book-controller/delete-book-by-id`
- **Get book by ID**: `GET /api/v1/book-controller/book-by-id`
- **Get all books**: `GET /api/v1/book-controller/all-books`
- **Search books by ISBN**: `GET /api/v1/book-controller/isbn-search`

### Reaction

- **React to multiple books**: `POST /api/v1/react-controller/react-to-all-book`
- **React to a single book**: `POST /api/v1/react-controller/react-to-book`
- **Get like count for a book**: `GET /api/v1/react-controller/book-like-count`
- **Get dislike count for a book**: `GET /api/v1/react-controller/book-dislike-count`
- **Update a reaction**: `PUT /api/v1/react-controller/change-reaction`
- **Get books ordered by reactions**: `GET /api/v1/react-controller/get-book-with-react`

### User

- **Register a new user**: `POST /api/v1/user-controller/user-registered`

## Running the Backend

### Prerequisites

- Java 17 
- Spring Boot V 3.2.7
- MySQL database

### Steps to Run

1. **Clone the Repository**:
    ```sh
    git clone https://github.com/DMGCD/book-shop-management.git
    cd bookshop-management
    ```

2. **Setup MySQL Database**:
   - Ensure MySQL is running and create a database named `bookshop`.
   - Update the `application.properties` file with your MySQL configurations.

3. **Build the Project**:
    ```sh
    ./mvnw clean install
    ```

4. **Run the Application**:
    ```sh
    ./mvnw spring-boot:run
    ```

5. **Accessing the Application**:
    - The backend will be running at `http://localhost:8083`.
    - Use Swagger  API client to interact with the endpoints.

## Database Configuration

Ensure your `application.properties` file contains the following configurations:

```properties
spring.application.name=book_shop_management
server.port=8083
springdoc.swagger-ui.enabled=true
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/bookshop?createDatabaseIfNotExist=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.datasource.username=root
#For Email
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=gcdisanayaka1998@gmail.com
spring.mail.password=ulxrttmqosmdknxg
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true




