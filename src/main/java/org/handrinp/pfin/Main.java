package org.handrinp.pfin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // load in account info
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter the account name: ");
        String name = kb.nextLine();
        Account account = Account.load(name);

        if (account == null) {
            account = new Account(name);
            System.out.println("New account created successfully.");
        } else {
            System.out.println("Loaded account successfully.");
        }

        boolean cont = true;

        // main central loop
        while (cont) {
            printTransactions(account);
            System.out.println("What would you like to do?");
            System.out.println("0) Quit");
            System.out.println("1) Add transaction");
            System.out.print("> ");
            int choice = Integer.parseInt(kb.nextLine());

            if (choice == 1) {
                System.out.print("Enter the amount: ");
                double amount = Double.parseDouble(kb.nextLine());
                System.out.print("Enter the transaction message: ");
                String message = kb.nextLine();
                System.out.print("Enter the transaction time: ");
                String time = kb.nextLine();
                account.addTransaction(amount, message, time);
            } else cont = false;
        }

        // close app
        boolean success = account.save();
        System.out.println(success ? "Account saved successfully." : "Account save failed!");
        kb.close();
    }

    public static void printTransactions(Account account) {
        System.out.println();
        System.out.println("============================================================");

        for (Transaction t : account.getTransactions()) {
            String trunc = t.getMessage();

            if (trunc.length() > 20) {
                trunc = trunc.substring(0, 17) + "...";
            }

            System.out.printf("%20s    %23s    %+9.2f%n", trunc, t.getTime(), t.getAmount());
        }

        System.out.printf("======================================== Total: %8.2f ===%n", account.getBalance());
        System.out.println();
    }
}

