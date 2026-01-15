package com.bankease.service;

import com.bankease.dao.UserDAO;
import com.bankease.dao.AccountDAO;
import com.bankease.model.User;
import com.bankease.model.Account;

import java.util.Random;

public class UserService {

    private UserDAO userDAO;
    private AccountDAO accountDAO;

    public UserService() {
        userDAO = new UserDAO();
        accountDAO = new AccountDAO();
    }


    // ------------------------------
    // USER REGISTRATION
    // ------------------------------
    public boolean register(String name, String email, String password) {

        // Check duplicate email
        if (userDAO.getUserByEmail(email) != null) {
            System.out.println("❌ Email already registered.");
            return false;
        }

        User user = new User(name, email, password);

        boolean created = userDAO.createUser(user);

        if (created) {
            System.out.println("✅ User registered successfully!");
        }

        return created;
    }


    // ------------------------------
    // USER LOGIN
    // ------------------------------
    public User login(String email, String password) {

        User user = userDAO.getUserByEmail(email);

        if (user == null) {
            System.out.println("❌ User not found.");
            return null;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("❌ Incorrect password.");
            return null;
        }

        System.out.println("✅ Login successful! Welcome, " + user.getName());
        return user;
    }


    // ------------------------------
    // CREATE BANK ACCOUNT FOR USER
    // ------------------------------
    public boolean createAccount(int userId, String type, double initialDeposit) {

        // Auto generate account number
        String accountNumber = generateAccountNumber();

        Account account = new Account(
                userId,
                accountNumber,
                type,
                initialDeposit
        );

        boolean created = accountDAO.createAccount(account);

        if (created) {
            System.out.println("✅ Account Created Successfully!");
            System.out.println("🆔 Account Number: " + accountNumber);
        }

        return created;
    }


    // ------------------------------
    // Utility → Generate Account Number
    // ------------------------------
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // 12-digit random account number
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}
