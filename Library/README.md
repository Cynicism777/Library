# 图书馆系统

## 说明

图书馆系统是我们的软件工程项目，使用了Spring Boot + Maven + MySQL + Thymeleaf 进行开发。在第一次迭代过程中，我们实现了管理员的登录验证以及对于图书信息的增删查。在第二次迭代过程中，我们新增或修改了如下的功能：

* **读者表**：新增了读者类，在数据库中创建相应的`reader`表，设计如下：

  | 字段名   | 数据类型 | 描述                           |
  | -------- | -------- | ------------------------------ |
  | id       | int      | 用于存放读者id，主键，自动递增 |
  | username | varchar  | 读者的用户名                   |
  | password | varchar  | 读者的密码                     |
  | penalty  | int      | 读者应该上交的罚金，默认为0    |

* **图书表**：修改了`book`表，新增图书状态栏`status`：`status=1`表明图书可借出，`status=0`表示图书已借出。

* **读者控制**：添加了管理员对于读者的新增、查找和删除的功能。管理员登录后会跳转到`Dashboard`管理员操作界面，该界面提供了图书管理（第一次迭代实现）和读者管理两个按钮，点击跳转到相应操作界面。

* **读者登录登出**：读者需要在`\reader\readerLogin`界面验证身份登录后，进入`readerDashboard`读者操作界面，该界面包含 “借阅图书” 按钮以及读者登出。

* **借阅图书**：读者登录验证后可以进入借阅图书界面，该界面类似于查找图书页面，状态栏显示图书借阅状态，右侧包含**借阅**操作按钮，点击并确认后申请借阅。系统会检查图书`status`是否为1且读者`penalty`是否为0，条件满足即可借出并返回成功信息，失败则返回失败理由。

## 项目工程目录结构

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.library
│   │   │       ├── controller
│   │   │       │   ├── AdminController.java
│   │   │       │   ├── BookController.java
│   │   │       │   ├── BorrowController.java
│   │   │       │   ├── DashboardController.java
│   │   │       │   └── ReaderController.java
│   │   │       ├── mapper
│   │   │       │   ├── AdminMapper.java
│   │   │       │   ├── BookMapper.java
│   │   │       │   └── ReaderMapper.java
│   │   │       ├── model
│   │   │       │   ├── Admin.java
│   │   │       │   ├── Book.java
│   │   │       │   └── Reader.java
│   │   │       └── service
│   │   │           ├── AdminService.java
│   │   │           ├── AdminServiceImpl.java
│   │   │           ├── BookService.java
│   │   │           ├── BookServiceImpl.java
│   │   │           ├── ReaderService.java
│   │   │           └── ReaderServiceImpl.java
│   │   └── resources
│   │       ├── templates
│   │       │   ├── addBook.html
│   │       │   ├── addReader.html
│   │       │   ├── bookManage.html
│   │       │   ├── borrow.html
│   │       │   ├── dashboard.html
│   │       │   ├── login.html
│   │       │   ├── readerDashboard.html
│   │       │   ├── readerLogin.html
│   │       │   ├── readerManage.html
│   │       │   ├── searchBook.html
│   │       │   └── searchReader.html
│   │       └── application.yml
│   └── test
├── pom.xml
└── README.md
```

## 项目说明

## 项目说明

### controller

- `AdminController`：处理管理员登录相关操作。
- `BookController`：处理图书的增删查操作。
- `BorrowController`：处理借阅图书相关操作。
- `DashboardController`：处理管理员操作界面。
- `ReaderController`：处理读者相关操作。

### mapper

- `AdminMapper`：与管理员表相关的数据库操作。
- `BookMapper`：与图书表相关的数据库操作。
- `ReaderMapper`：与读者表相关的数据库操作。

### model

- `Admin`：管理员模型。
- `Book`：图书模型。
- `Reader`：读者模型。

### service

- `AdminService`：处理管理员相关逻辑。
- `AdminServiceImpl`：`AdminService` 接口的实现。
- `BookService`：处理图书相关逻辑。
- `BookServiceImpl`：`BookService` 接口的实现。
- `ReaderService`：处理读者相关逻辑。
- `ReaderServiceImpl`：`ReaderService` 接口的实现。

### templates

- `addBook.html`：添加图书页面。
- `addReader.html`：添加读者页面。
- `bookManage.html`：图书管理主页面。
- `borrow.html`：借阅图书页面。
- `dashboard.html`：管理员操作界面。
- `login.html`：管理员登录页面。
- `readerDashboard.html`：读者操作界面。
- `readerLogin.html`：读者登录页面。
- `readerManage.html`：读者管理页面。
- `searchBook.html`：查询图书页面。
- `searchReader.html`：查询读者页面。

### resources

- `application.yml`：配置文件，包含数据库连接等相关配置。