package com.bankease.ui;

import javax.swing.*;
import java.awt.*;
import com.bankease.model.User;

public class DashboardUI extends JFrame {

    // private User user;

    public DashboardUI(User user) {
        // this.user = user;

        setTitle("BankEase Dashboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 1, 10, 10));

        JLabel welcome = new JLabel("Welcome, " + user.getName(), SwingConstants.CENTER);

        JButton createAcc = new JButton("Create Account");
        JButton viewAcc = new JButton("View Accounts");
        JButton deposit = new JButton("Deposit");
        JButton withdraw = new JButton("Withdraw");
        JButton transfer = new JButton("Transfer");
        JButton viewTx = new JButton("View Transactions");
        JButton logout = new JButton("Logout");

        add(welcome);
        add(createAcc);
        add(viewAcc);
        add(deposit);
        add(withdraw);
        add(transfer);
        add(viewTx);
        add(logout);

        createAcc.addActionListener(e -> new CreateAccountUI(user));
        viewAcc.addActionListener(e -> new ViewAccountsUI(user));
        deposit.addActionListener(e -> new DepositUI(user));
        withdraw.addActionListener(e -> new WithdrawUI(user));
        transfer.addActionListener(e -> new TransferUI(user));
        viewTx.addActionListener(e -> new TransactionsUI(user));

        logout.addActionListener(e -> {
            dispose();
            new LoginUI();
        });

        setVisible(true);
    }
}
