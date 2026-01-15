# 🏦 **BankEase – Desktop Banking Application (Java Swing + MySQL Cloud)**

BankEase is a fully functional desktop banking application built using **Java**, **Swing UI**, **MySQL Cloud**, and **Maven**.
It supports secure user registration, login, account creation, deposits, withdrawals, transfers, and full transaction history.

This project demonstrates **object-oriented design**, **Java Swing GUI development**, **JDBC database integration**, and **clean layering using DAO + Service + UI**.

---

# 🚀 Features

### 👤 **User Management**

* Register a new user
* Secure login
* Unique email validation

### 🏦 **Bank Accounts**

* Create Savings / Current accounts
* Auto-generated 12-digit account numbers
* View all accounts
* View account balances

### 💰 **Money Operations**

* Deposit money
* Withdraw money with balance validation
* Transfer between accounts
* Records every operation in transactions table

### 📜 **Transaction History**

* View all transactions of an account
* Shows type, amount, balance after, and timestamp

### 🗃️ **MySQL Cloud Integration**

* All user & account data stored in cloud database
* JDBC connection using `.env` secure config

### 🖥️ **Modern Desktop UI (Java Swing)**

* Login screen
* Registration screen
* Dashboard
* Account creation window
* Deposit / Withdraw / Transfer UI
* Transaction history viewer

---

# 🏗️ Project Architecture

```
bankease/
 ├── src/main/java/
 │   ├── com/bankease/
 │   │    ├── App.java                 # Swing app entry point
 │   │
 │   │    ├── model/                   # POJO models
 │   │    │     ├── User.java
 │   │    │     ├── Account.java
 │   │    │     └── Transaction.java
 │   │
 │   │    ├── dao/                     # DAO layer (database operations)
 │   │    │     ├── UserDAO.java
 │   │    │     ├── AccountDAO.java
 │   │    │     └── TransactionDAO.java
 │   │
 │   │    ├── service/                 # Business logic
 │   │    │     ├── UserService.java
 │   │    │     └── AccountService.java
 │   │
 │   │    └── ui/                      # Swing UI windows
 │   │         ├── LoginUI.java
 │   │         ├── RegisterUI.java
 │   │         ├── DashboardUI.java
 │   │         ├── CreateAccountUI.java
 │   │         ├── ViewAccountsUI.java
 │   │         ├── DepositUI.java
 │   │         ├── WithdrawUI.java
 │   │         ├── TransferUI.java
 │   │         └── TransactionsUI.java
 │
 ├── .env                              # secure DB credentials (not in GitHub)
 ├── pom.xml                           # Maven dependencies
 └── README.md
```

---


# 🙋‍♂️ Author

**Sharvesh**
B.Tech CSBS
Java | Full Stack | Backend | MySQL
GitHub: https://github.com/sharveshl/

---

# ⭐ If you like this project, don't forget to star the repo
