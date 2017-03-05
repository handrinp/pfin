package org.handrinp.pfin;

public class Account {
    private String name;
    private double balance;

    public Account(String name) {
        this.name = name;
        this.balance = 0.0;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}

