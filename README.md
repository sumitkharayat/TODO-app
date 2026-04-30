#  TODO App — Full Stack Web Application

A full-stack To-Do web application built with Java Spring Boot, MySQL, and vanilla HTML/CSS/JavaScript.

🔗 **Live Demo:** https://todo-app-8q5j.onrender.com

## 🚀 Features
- Add tasks with title, description, priority and due date
- Smart status — auto detects In Progress vs Overdue based on date/time
- Mark tasks as completed
- Delete tasks
- Filter by All / In Progress / Overdue / Completed
- Stats dashboard (Total / In Progress / Overdue / Done)
- Data saved to real MySQL database

## 🛠️ Tech Stack
| Layer | Technology |
|-------|-----------|
| Backend | Java 25 + Spring Boot 4.0.5 |
| Database | MySQL 8 (Aiven Cloud) |
| ORM | Hibernate / JPA |
| Frontend | HTML + CSS + JavaScript |
| Server | Apache Tomcat (embedded) |
| Hosting | Render |

## 📁 Project Structure
```
src/main/java/com/example/todo/
├── TodoApplication.java
├── SecurityConfig.java
├── controller/
│   └── TodoController.java
├── model/
│   ├── Todo.java
│   └── User.java
├── repository/
│   ├── TodoRepository.java
│   └── UserRepository.java
└── service/
    └── TodoService.java
```

## ⚙️ Run Locally

### Prerequisites
- Java 17+
- Maven 3.9+
- MySQL 8

### Steps
```bash
# Clone the repo
git clone https://github.com/sumitkharayat/TODO-app.git
cd TODO-app

# Configure database in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/todo_app
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

# Run the app
mvn spring-boot:run
```

Open http://localhost:8080 in your browser.

## 🗄️ Database Schema
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE todos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    priority ENUM('LOW','MEDIUM','HIGH') DEFAULT 'MEDIUM',
    status ENUM('PENDING','IN_PROGRESS','COMPLETED') DEFAULT 'PENDING',
    due_date DATE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```

## 📄 License
This project is open source and available under the MIT License.

---
Developed by Sumit Kharayat
