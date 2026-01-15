package com.bankease.model;

public class Beneficiary {

    private int beneficiary_id;
    private int user_id;
    private String name;
    private String account_number;
    private String bank_name;

    public Beneficiary() {}

    public Beneficiary(int user_id, String name, String account_number, String bank_name) {
        this.user_id = user_id;
        this.name = name;
        this.account_number = account_number;
        this.bank_name = bank_name;
    }

    public int getBeneficiary_id() {
        return beneficiary_id;
    }
    public void setBeneficiary_id(int beneficiary_id) {
        this.beneficiary_id = beneficiary_id;
    }

    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_number() {
        return account_number;
    }
    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getBank_name() {
        return bank_name;
    }
    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
}
