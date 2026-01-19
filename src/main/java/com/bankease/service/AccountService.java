package com.bankease.service;

import com.bankease.dao.AccountDAO;
import com.bankease.dao.TransactionDAO;
import com.bankease.model.Account;
import com.bankease.model.Transaction;

public class AccountService {

    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;

    public AccountService() {
        this.accountDAO = new AccountDAO();
        this.transactionDAO = new TransactionDAO();
    }

    // ------------------------------
    // DEPOSIT
    // ------------------------------
    public boolean deposit(int accountId, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return false;
        }

        Account account = accountDAO.getAccountById(accountId);
        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }

        double newBalance = account.getBalance() + amount;

        // Update balance
        boolean updated = accountDAO.updateBalance(accountId, newBalance);

        // Add transaction record
        if (updated) {
            Transaction t = new Transaction(
                    accountId,
                    "DEPOSIT",
                    amount,
                    newBalance,
                    "Amount deposited"
            );
            transactionDAO.addTransaction(t);
        }

        return updated;
    }

    // ------------------------------
    // WITHDRAW
    // ------------------------------
    public boolean withdraw(int accountId, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdraw amount.");
            return false;
        }

        Account account = accountDAO.getAccountById(accountId);
        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }

        if (account.getBalance() < amount) {
            System.out.println("Insufficient funds.");
            return false;
        }

        double newBalance = account.getBalance() - amount;

        // Update balance
        boolean updated = accountDAO.updateBalance(accountId, newBalance);

        // Add transaction record
        if (updated) {
            Transaction t = new Transaction(
                    accountId,
                    "WITHDRAW",
                    amount,
                    newBalance,
                    "Amount withdrawn"
            );
            transactionDAO.addTransaction(t);
        }

        return updated;
    }

    public double fetchBalance(Account acc){
        return acc.getBalance();
    }

    // ------------------------------
    // TRANSFER
    // ------------------------------
    public boolean transfer(int fromAccountId, String toAccountNumber, double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount");
            return false;
        }

        Account fromAcc = accountDAO.getAccountById(fromAccountId);
        Account toAcc = accountDAO.getAccountByNumber(toAccountNumber);

        if (fromAcc == null || toAcc == null) {
            System.out.println("Account not found");
            return false;
        }

        if (fromAcc.getBalance() < amount) {
            System.out.println("Insufficient balance");
            return false;
        }

        double fromNewBal = fromAcc.getBalance() - amount;
        double toNewBal = toAcc.getBalance() + amount;

        accountDAO.updateBalance(fromAcc.getAccount_id(), fromNewBal);
        accountDAO.updateBalance(toAcc.getAccount_id(), toNewBal);

        transactionDAO.addTransaction(new Transaction(
                fromAcc.getAccount_id(),
                "TRANSFER_OUT",
                amount,
                fromNewBal,
                "Transfer to A/c " + toAcc.getAccount_number()
        ));

        transactionDAO.addTransaction(new Transaction(
                toAcc.getAccount_id(),
                "TRANSFER_IN",
                amount,
                toNewBal,
                "Transfer from A/c " + fromAcc.getAccount_number()
        ));

        return true;
    }

}
