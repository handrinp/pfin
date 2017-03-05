package org.handrinp.pfin;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private double balance;
    private List<Transaction> transactions;

    public Account(String name) {
        this.name = name;
        this.balance = 0.0;
        this.transactions = new ArrayList<Transaction>();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public int getTransactionCount() {
        return transactions.size();
    }

    public Transaction getTransaction(int i) {
        return transactions.get(i);
    }

    public void addTransaction(double amount, String message, String date) {
        Transaction t = new Transaction(amount, message, date);
        transactions.add(t);
    }
}

