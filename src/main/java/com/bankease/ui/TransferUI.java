package com.bankease.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.bankease.model.User;
import com.bankease.model.Account;
import com.bankease.dao.AccountDAO;
import com.bankease.service.AccountService;

public class TransferUI extends JFrame {

    private AccountService accountService = new AccountService();
    private AccountDAO accountDAO = new AccountDAO();

    public TransferUI(User user) {

        setTitle("Transfer Money");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel fromLbl = new JLabel("From Account:");
        JComboBox<String> fromList = new JComboBox<>();

        JLabel toLbl = new JLabel("To Account:");
        JComboBox<String> toList = new JComboBox<>();

        List<Account> accounts = accountDAO.getAccountsByUser(user.getUser_id());

        for (Account a : accounts) {
            fromList.addItem(a.getAccount_id() + "");
            toList.addItem(a.getAccount_id() + "");
        }

        JLabel amountLbl = new JLabel("Amount:");
        JTextField amountField = new JTextField();

        JButton transferBtn = new JButton("Transfer");

        add(fromLbl); add(fromList);
        add(toLbl); add(toList);
        add(amountLbl); add(amountField);
        add(new JLabel());
        add(transferBtn);

        transferBtn.addActionListener(e -> {
            int fromAcc = Integer.parseInt((String) fromList.getSelectedItem());
            int toAcc = Integer.parseInt((String) toList.getSelectedItem());
            double amount = Double.parseDouble(amountField.getText());

            if (fromAcc == toAcc) {
                JOptionPane.showMessageDialog(null, "Cannot transfer to the same account!");
                return;
            }

            accountService.transfer(fromAcc, toAcc, amount);
            JOptionPane.showMessageDialog(null, "Transfer Successful!");
            dispose();
        });

        setVisible(true);
    }
}
