package com.bankease.ui;

import javax.swing.*;
import java.util.List;

import com.bankease.dao.AccountDAO;
import com.bankease.model.Account;
import com.bankease.model.User;

public class ViewAccountsUI extends JFrame {

    private AccountDAO accountDAO = new AccountDAO();

    public ViewAccountsUI(User user) {

        setTitle("My Accounts");
        setSize(400, 300);
        setLocationRelativeTo(null);

        List<Account> accounts = accountDAO.getAccountsByUser(user.getUser_id());

        JTextArea area = new JTextArea();
        area.setEditable(false);

        if (accounts.isEmpty()) {
            area.setText("No accounts created yet.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Account a : accounts) {
                sb.append("Account ID: ").append(a.getAccount_id()).append("\n")
                  .append("Type: ").append(a.getAccount_type()).append("\n")
                  .append("Number: ").append(a.getAccount_number()).append("\n")
                  .append("Balance: ₹").append(a.getBalance()).append("\n")
                  .append("---------------------------\n");
            }
            area.setText(sb.toString());
        }

        add(new JScrollPane(area));
        setVisible(true);
    }
}
