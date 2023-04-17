# 图书管理系统

## 说明

图书管理系统是我们软件工程项目图书馆系统的第一次实现的部分，使用了Spring Boot + Maven + MySQL + Thymeleaf 进行开发。实现了一个简单的web应用程序，管理员可以验证通过后登录进行图书管理，即进行增添图书，查找图书和删除图书的操作。

## 项目工程目录结构

```plaintext
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.library
│   │   │       ├── controller
│   │   │       │   ├── AdminController.java
│   │   │       │   └── BookController.java
│   │   │       ├── mapper
│   │   │       │   ├── AdminMapper.java
│   │   │       │   └── BookMapper.java
│   │   │       ├── model
│   │   │       │   ├── Admin.java
│   │   │       │   └── Book.java
│   │   │       └── service
│   │   │           ├── AdminService.java
│   │   │           ├── AdminServiceImpl.java
│   │   │           ├── BookService.java
│   │   │           └── BookServiceImpl.java
│   │   └── resources
│   │       ├── templates
│   │       │   ├── addBook.html
│   │       │   ├── bookManage.html
│   │       │   ├── login.html
│   │       │   └── searchBook.html
│   │       └── application.yml
│   └── test
├── pom.xml
└── README.md

```

## 项目说明

### controller

- `AdminController`：处理管理员登录相关操作。
- `BookController`：处理图书的增删查操作。

### mapper

- `AdminMapper`：与管理员表相关的数据库操作。
- `BookMapper`：与图书表相关的数据库操作。

### model

- `Admin`：管理员模型。
- `Book`：图书模型。

### service

- `AdminService`：处理管理员相关逻辑。
- `AdminServiceImpl`：`AdminService` 接口的实现。
- `BookService`：处理图书相关逻辑。
- `BookServiceImpl`：`BookService` 接口的实现。

### templates

- `addBook.html`：添加图书页面。
- `bookManage.html`：图书管理主页面。
- `login.html`：管理员登录页面。
- `searchBook.html`：查询图书页面。

