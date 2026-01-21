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
                String fromAccNo = fromText.split("\\s+")[0];

                String toAccNo = toAccField.getText();
                double amount = Double.parseDouble(amtField.getText());

                int choice = JOptionPane.showConfirmDialog(null, 
                    "Do you want to transfer the amount?",
                    "Confirmation",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                boolean success = false;
                if(choice==JOptionPane.YES_OPTION){
                    success = accountService.transfer(fromAccNo, toAccNo, amount);
                    System.out.println("User confirmation is YES");
                }
                else if(choice==JOptionPane.NO_OPTION){
                    success = false;
                    System.out.println("User cancelled  the transaction");
                } 
                else{
                    System.out.println("User chose Cancel or closed the confirmation dialog for transaction.");
                }

                if (success) {
                    JOptionPane.showMessageDialog(null, "Transfer Successful!");
                    dispose();
                    System.out.println("Transfer Successfull");
                } else {
                    JOptionPane.showMessageDialog(null, "Transfer Failed!");
                    System.out.println("Transfer Failed");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input!");
            }
        });

        fetchBalance.addActionListener(e ->{
            // List<Account> acc = accountDAO.getAccountsByUser(user.getUser_id());
            String fromText = (String) fromBox.getSelectedItem();
            String slectedAccNo = fromText.split(" ")[0];
            Account selectedAccount = accountDAO.getAccountByNumber(slectedAccNo);
            if(selectedAccount!=null)
                balancField.setText("Balance: ₹" + accountService.fetchBalance(selectedAccount));
            else{
                JOptionPane.showMessageDialog(null, "No Account is present");
            }

        });
        
        setVisible(true);
    }
}
