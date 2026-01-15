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

    // ------------------------------
    // TRANSFER
    // ------------------------------
    public boolean transfer(int fromAccountId, int toAccountId, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid transfer amount.");
            return false;
        }

        Account fromAcc = accountDAO.getAccountById(fromAccountId);
        Account toAcc = accountDAO.getAccountById(toAccountId);

        if (fromAcc == null || toAcc == null) {
            System.out.println("Account not found.");
            return false;
        }

        if (fromAcc.getBalance() < amount) {
            System.out.println("Insufficient funds.");
            return false;
        }

        // Deduct from source
        double newFromBalance = fromAcc.getBalance() - amount;
        boolean fromUpdated = accountDAO.updateBalance(fromAccountId, newFromBalance);

        // Add to destination
        double newToBalance = toAcc.getBalance() + amount;
        boolean toUpdated = accountDAO.updateBalance(toAccountId, newToBalance);

        if (fromUpdated && toUpdated) {

            // Outgoing transfer transaction
            Transaction outTx = new Transaction(
                    fromAccountId,
                    "TRANSFER_OUT",
                    amount,
                    newFromBalance,
                    "Transfer sent to Account: " + toAccountId
            );
            transactionDAO.addTransaction(outTx);

            // Incoming transfer transaction
            Transaction inTx = new Transaction(
                    toAccountId,
                    "TRANSFER_IN",
                    amount,
                    newToBalance,
                    "Transfer received from Account: " + fromAccountId
            );
            transactionDAO.addTransaction(inTx);

            return true;

        } else {
            System.out.println("Transfer failed.");
            return false;
        }
    }
}
