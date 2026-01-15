package com.bankease.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.bankease.model.User;
import com.bankease.model.Account;
import com.bankease.dao.AccountDAO;
import com.bankease.service.AccountService;

public class WithdrawUI extends JFrame {

    private AccountService accountService = new AccountService();
    private AccountDAO accountDAO = new AccountDAO();

    public WithdrawUI(User user) {

        setTitle("Withdraw Money");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel accLbl = new JLabel("Select Account:");
        JComboBox<String> accList = new JComboBox<>();

        List<Account> accounts = accountDAO.getAccountsByUser(user.getUser_id());

        for (Account a : accounts) {
            accList.addItem(a.getAccount_id() + "");
        }

        JLabel amountLbl = new JLabel("Amount:");
        JTextField amountField = new JTextField();

        JButton withdrawBtn = new JButton("Withdraw");

        add(accLbl);
        add(accList);
        add(amountLbl);
        add(amountField);
        add(new JLabel());
        add(withdrawBtn);

        withdrawBtn.addActionListener(e -> {
            int accId = Integer.parseInt((String) accList.getSelectedItem());
            double amount = Double.parseDouble(amountField.getText());

            accountService.withdraw(accId, amount);
            JOptionPane.showMessageDialog(null, "Withdraw Successful!");
            dispose();
        });

        setVisible(true);
    }
}
