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

        setTitle("Money Transfer");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        JLabel fromLbl = new JLabel("From Account:");
        JComboBox<String> fromBox = new JComboBox<>();

        List<Account> accounts = accountDAO.getAccountsByUser(user.getUser_id());
        for (Account a : accounts) {
            fromBox.addItem(a.getAccount_number() + "  " + a.getAccount_type());
        }

        JLabel toLbl = new JLabel("To Account Number:");
        JTextField toAccField = new JTextField();

        JLabel amtLbl = new JLabel("Amount:");
        JTextField amtField = new JTextField();
        JButton fetchBalance = new JButton("FetchBalance");
        JButton transferBtn = new JButton("Transfer");
        JLabel balancField = new JLabel("Balance: ₹0.00");
        add(fromLbl); add(fromBox);
        add(toLbl); add(toAccField);
        add(amtLbl); add(amtField);
        add(fetchBalance);
        add(balancField);
        add(new JLabel()); add(transferBtn);

        transferBtn.addActionListener(e -> {
            try {
                String fromText = (String) fromBox.getSelectedItem();
                int fromAccId = Integer.parseInt(fromText.split(" ")[0]);

                String toAccNo = toAccField.getText();
                double amount = Double.parseDouble(amtField.getText());

                boolean success = accountService.transfer(fromAccId, toAccNo, amount);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Transfer Successful!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Transfer Failed!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input!");
            }
        });

        fetchBalance.addActionListener(e ->{
            // List<Account> acc = accountDAO.getAccountsByUser(user.getUser_id());
            String fromText = (String) fromBox.getSelectedItem();
            String slectedAccNo = fromText.split(" ")[0];
            Account selectedAccount = null;
            for(Account i:accounts){
                if(i.getAccount_number().equals(slectedAccNo)){
                    selectedAccount = i;
                    break;
                }
            }
            if(selectedAccount!=null)
                balancField.setText("Balance: ₹" + accountService.fetchBalance(selectedAccount));
            else{
                JOptionPane.showMessageDialog(null, "No Account is present");
            }

        });

        setVisible(true);
    }
}
