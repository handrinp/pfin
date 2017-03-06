package org.handrinp.pfin;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {
    private String name;
    private String saltedHash;
    private double balance;
    private List<Transaction> transactions;

    public Account(String name, String password) {
        this.name = name;
        this.saltedHash = Crypto.getSaltedHash(password);
        this.balance = 0.0;
        this.transactions = new ArrayList<Transaction>();
    }

    public String getName() {
        return name;
    }

    public String getSaltedHash() {
        return saltedHash;
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof Account) {
            Account that = (Account) o;

            if (name.equals(that.getName())
                        && Math.abs(balance - that.getBalance()) < Constants.DELTA) {
                if (getTransactionCount() == that.getTransactionCount()) {
                    return transactions.containsAll(that.getTransactions());
                }
            }
        }

        return false;
    }

    // JSON utilities

    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public static Account fromJson(String json) {
        Gson gson = new Gson();
        Account account = gson.fromJson(json, Account.class);
        return account;
    }

    // File utilities

    public String getFileName() {
        return getFileName(name);
    }

    public static boolean exists(String name) {
        File f = new File(getFileName(name));
        return f.isFile();
    }

    public static String getFileName(String name) {
        String fixedName = name.trim().toLowerCase().replaceAll("\\s+", "-");
        return fixedName + ".json";
    }

    public boolean save() {
        boolean success = true;

        try (PrintWriter pw = new PrintWriter(getFileName())) {
            String json = toJson();
            pw.println(json);
        } catch (FileNotFoundException e) {
            // fail silently
            success = false;
        }

        return success;
    }

    public static Account load(String name, String password) {
        Account account;

        try (Scanner in = new Scanner(new File(getFileName(name)))) {
            String json = in.nextLine();
            account = fromJson(json);
            if (!Crypto.check(password, account.getSaltedHash())) throw new Exception();
        } catch (Exception e) {
            account = null;
        }

        return account;
    }
}

