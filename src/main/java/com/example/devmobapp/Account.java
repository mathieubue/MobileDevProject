package com.example.devmobapp;

public class Account {
    private int id;
    private String accountName;
    private double amount;
    private String iban;
    private String currency;

    public int getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getAmount() {
        return amount;
    }

    public String getIban() {
        return iban;
    }

    public String getCurrency() {
        return currency;
    }
}
