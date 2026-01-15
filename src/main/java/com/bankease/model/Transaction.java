package com.bankease.model;

public class Transaction {

    private int transaction_id;
    private int account_id;
    private String type;
    private double amount;
    private double balance_after;
    private String description;

    public Transaction() {}

    public Transaction(int account_id, String type, double amount, double balance_after, String description) {
        this.account_id = account_id;
        this.type = type;
        this.amount = amount;
        this.balance_after = balance_after;
        this.description = description;
    }

    public int getTransaction_id() {
        return transaction_id;
    }
    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getAccount_id() {
        return account_id;
    }
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance_after() {
        return balance_after;
    }
    public void setBalance_after(double balance_after) {
        this.balance_after = balance_after;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
