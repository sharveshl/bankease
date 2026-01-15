package com.bankease.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.bankease.dao.AccountDAO;
import com.bankease.dao.TransactionDAO;
import com.bankease.model.User;
import com.bankease.model.Account;
import com.bankease.model.Transaction;

public class TransactionsUI extends JFrame {

    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    public TransactionsUI(User user) {

        setTitle("Transaction History");
        setSize(450, 400);
        setLocationRelativeTo(null);

        List<Account> accounts = accountDAO.getAccountsByUser(user.getUser_id());

        String[] accIds = accounts.stream()
                .map(a -> a.getAccount_id() + "")
                .toArray(String[]::new);

        JComboBox<String> accBox = new JComboBox<>(accIds);
        JTextArea area = new JTextArea();
        area.setEditable(false);

        JButton loadBtn = new JButton("Load Transactions");

        JPanel top = new JPanel();
        top.add(new JLabel("Select Account:"));
        top.add(accBox);
        top.add(loadBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        loadBtn.addActionListener(e -> {
            int accId = Integer.parseInt((String) accBox.getSelectedItem());
            List<Transaction> txs = transactionDAO.getTransactionsByAccount(accId);

            if (txs.isEmpty()) {
                area.setText("No transactions found.");
                return;
            }

            StringBuilder sb = new StringBuilder();

            for (Transaction t : txs) {
                sb.append("ID: ").append(t.getTransaction_id()).append("\n")
                  .append("Type: ").append(t.getType()).append("\n")
                  .append("Amount: ₹").append(t.getAmount()).append("\n")
                  .append("Balance After: ₹").append(t.getBalance_after()).append("\n")
                  .append("Description: ").append(t.getDescription()).append("\n")
                  .append("------------------------------------------------\n");
            }

            area.setText(sb.toString());
        });

        setVisible(true);
    }
}
