package com.bankease.ui;

import javax.swing.*;
import java.awt.*;
import com.bankease.service.UserService;

public class RegisterUI extends JFrame {

    private UserService userService = new UserService();

    public RegisterUI() {

        setTitle("BankEase - Register");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel nameLbl = new JLabel("Name:");
        JLabel emailLbl = new JLabel("Email:");
        JLabel passLbl = new JLabel("Password:");

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passField = new JPasswordField();

        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("Back");

        add(nameLbl); add(nameField);
        add(emailLbl); add(emailField);
        add(passLbl); add(passField);
        add(registerBtn); add(backBtn);

        registerBtn.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passField.getPassword());

            boolean success = userService.register(name, email, password);

            if (success) {
                JOptionPane.showMessageDialog(null, "Registration Successful!");
                dispose();
                new LoginUI();
            } else {
                JOptionPane.showMessageDialog(null, "Email already exists!");
            }
        });

        backBtn.addActionListener(e -> {
            dispose();
            new LoginUI();
        });

        setVisible(true);
    }
}
