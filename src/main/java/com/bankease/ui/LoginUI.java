package com.bankease.ui;

import javax.swing.*;
import java.awt.*;
import com.bankease.service.UserService;
import com.bankease.model.User;
import java.net.URL;

public class LoginUI extends JFrame {

    private UserService userService = new UserService();
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registrationButton; 

    public LoginUI(){
        setUpFrame();
    }
    private void setUpFrame(){
        setSize(800, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Bankease Login");
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,2));
        JPanel left = createLeftPanel();
        JPanel right = createRightPanel();
        add(left);
        add(right);
        setVisible(true);
    }

    private JPanel createLeftPanel(){
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(6, 1, 10, 10));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        emailField = new JTextField("Enter Your Email");
        passwordField = new JPasswordField("Enter Your Password");
        loginButton = new JButton("Login");
        registrationButton = new JButton("Register");
        leftPanel.add(new JLabel("Email:"));
        leftPanel.add(emailField);
        leftPanel.add(new JLabel("Password:"));
        leftPanel.add(passwordField);
        leftPanel.add(loginButton);
        leftPanel.add(registrationButton);
        loginButton.addActionListener(e -> handleLogin());
        registrationButton.addActionListener(e -> handleRegistration());
        return leftPanel;
    }

    private JPanel createRightPanel(){
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE); // Add this to see if the panel itself appears
        rightPanel.setLayout(new BorderLayout());

        // Use the Thread context class loader as a fallback
        URL imageUrl = getClass().getResource("/bank.png");
        ImageIcon icon = new ImageIcon(imageUrl);
        Image img = icon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        rightPanel.add(label, BorderLayout.CENTER);
        return rightPanel;
    }

    private void handleLogin() {
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
    }
    private void handleRegistration() {
        dispose();
        new RegisterUI();
    }

}
