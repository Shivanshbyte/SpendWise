<h1 align="center">🌟 My Wallet - Expense tracking app 🌟</h1>

<p align="center">
  <img alt="Static Badge" src="https://img.shields.io/badge/Spring%20Boot-darkgreen?style=for-the-badge">
  <img alt="Static Badge" src="https://img.shields.io/badge/React.js-blue?style=for-the-badge">
  <img alt="Static Badge" src="https://img.shields.io/badge/mysql-red?style=for-the-badge">
  <img alt="Static Badge" src="https://img.shields.io/badge/css-purple?style=for-the-badge">
  <img alt="Static Badge" src="https://img.shields.io/badge/jwt-orange?style=for-the-badge">
</p>

## Table of contents

1. [Descripiton](#description)
2. [How to run?](#how-to-run)
3. [Screenshots](#screenshots)

## Description

- Developed a full-stack expense tracking web application using Spring Boot, React.js, and MySQL, facilitating seamless management of day-to-day finances.
- Implemented multi-role functionality with user authentication, enabling secure access for both users and administrators, with features such as sign-in, sign-up, password reset, and email verification.
- Developed intuitive user dashboards, transaction management, upcoming/recurring transactions tracking, monthly summaries, and statistics, budget management.
- Developed categories, users and transactions management for administrators.
- Implemented management capabilities including search, filter and pagination.

## How to run?

### Step 1: Fork and Clone the Repository

1. Fork the repository to your GitHub account.

2. Clone the forked repository to your local machine.

```sh
git clone https://github.com/<your-username>/Fullstack-Expense-Tracker
```

### Step 2: Setting up e-mail and database configurations

- Configure the following credentials in the [`application.properties`](https://github.com/DharshiBalasubramaniyam/Fullstack-Expense-Tracker/blob/main/backend/src/main/resources/application.properties) file.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.mail.username=YOUR_USERNAME
spring.mail.password=YOUR_PASSWORD
```

### Step 3: Run the backend.

- Run the backend application. It will automatically create the required tables. 
- Add some custom data manually in the [categories](https://github.com/DharshiBalasubramaniyam/Fullstack-Expense-Tracker/blob/7ecea71aaeca4e26a4aafd02fd602abe4d9da67d/backend/src/main/java/com/fullStack/expenseTracker/models/Category.java#L13) table for both [type](https://github.com/DharshiBalasubramaniyam/Fullstack-Expense-Tracker/blob/7ecea71aaeca4e26a4aafd02fd602abe4d9da67d/backend/src/main/java/com/fullStack/expenseTracker/models/TransactionType.java#L13) `expense` and `income`.
- To start as admin, Insert a new user manually with role admin in [`users`](https://github.com/DharshiBalasubramaniyam/Fullstack-Expense-Tracker/blob/7ecea71aaeca4e26a4aafd02fd602abe4d9da67d/backend/src/main/java/com/fullStack/expenseTracker/models/User.java#L20) table.

### Step 4: Run the frontend

1. Navigate to [frontend direcory](https://github.com/DharshiBalasubramaniyam/Fullstack-Expense-Tracker/tree/main/frontend).
```
cd ./frontend
```

2. Install dependencies.
```
npm install
```

3. Run the app.
```
npm start
```

Access the application at [`http://localhost:3000/`](http://localhost:3000/).
To get started create a new account using your email.

