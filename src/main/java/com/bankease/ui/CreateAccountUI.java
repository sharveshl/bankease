package com.bankease.ui;

import javax.swing.*;
import java.awt.*;

import com.bankease.model.User;
import com.bankease.service.UserService;

public class CreateAccountUI extends JFrame {

    private UserService userService = new UserService();

    public CreateAccountUI(User user) {

        setTitle("Create Bank Account");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel typeLbl = new JLabel("Account Type:");
        String[] types = {"SAVINGS", "CURRENT"};
        JComboBox<String> typeBox = new JComboBox<>(types);

        JLabel depositLbl = new JLabel("Initial Deposit:");
        JTextField depositField = new JTextField();

        JButton createBtn = new JButton("Create Account");

        add(typeLbl);
        add(typeBox);
        add(depositLbl);
        add(depositField);
        add(new JLabel());
        add(createBtn);

        createBtn.addActionListener(e -> {
            String type = (String) typeBox.getSelectedItem();
            double amount = Double.parseDouble(depositField.getText());

            boolean created = userService.createAccount(user.getUser_id(), type, amount);

            if (created) {
                JOptionPane.showMessageDialog(null, "Account Created Successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to create account!");
            }
        });

        setVisible(true);
    }
}
