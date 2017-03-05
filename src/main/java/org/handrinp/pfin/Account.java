package org.handrinp.pfin;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Transaction getTransaction(int i) {
        return transactions.get(i);
    }

    public void addTransaction(double amount, String message, String date) {
        balance += amount;
        Transaction t = new Transaction(amount, message, date);
        transactions.add(t);
    }

    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public static Account fromJson(String json) {
        Gson gson = new Gson();
        Account a = gson.fromJson(json, Account.class);
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Account) {
            Account that = (Account) o;

            if (name.equals(that.getName()) && Math.abs(balance - that.getBalance()) < Constants.DELTA) {
                if (getTransactionCount() == that.getTransactionCount()) {
                    return transactions.containsAll(that.getTransactions());
                }
            }
        }

        return false;
    }
}

