package org.handrinp.pfin;

public class Transaction {
    private double amount;
    private String message;
    private String time;

    public Transaction(double amount, String message, String time) {
        this.amount = amount;
        this.message = message;
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}

