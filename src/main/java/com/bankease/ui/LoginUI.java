package com.bankease.ui;

import javax.swing.*;
import java.awt.*;
import com.bankease.service.UserService;
import com.bankease.model.User;

public class LoginUI extends JFrame {

    private UserService userService = new UserService();

    public LoginUI() {

        setTitle("BankEase - Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginBtn);
        add(registerBtn);

        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            User user = userService.login(email, password);

            if (user != null) {
                JOptionPane.showMessageDialog(null, "Login Successful!");
                dispose();
                new DashboardUI(user);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid email or password!");
            }
        });

        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterUI();
        });

        setVisible(true);
    }
}
