package org.handrinp.pfin;

import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // load in account info
        Console console = System.console();

        if (console == null) {
            System.out.println("Couldn't get the console instance.");
            System.exit(0);
        }

        try (Scanner kb = new Scanner(System.in, "UTF-8")) {
            System.out.print("Enter the account name: ");
            String name = kb.nextLine();
            String password = new String(console.readPassword("Enter your password: "));
            Account account;

            if (Account.exists(name)) {
                account = Account.load(name, password);

                if (account == null) {
                    System.out.println("Incorrect password.");
                    System.exit(0);
                } else {
                    System.out.println("Loaded account successfully.");
                }
            } else {
                account = new Account(name, password);
                System.out.println("New account created successfully.");
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
        } catch (IllegalArgumentException e) {} // this will never happen, since UTF-8 encoding exists
    }

    public static void printTransactions(Account account) {
        System.out.println();
        System.out.println("================================================================");

        for (Transaction t : account.getTransactions()) {
            String fixedMessage = t.getMessage();

            if (fixedMessage.length() > 20) {
                fixedMessage = fixedMessage.substring(0, 17) + "...";
            }

            System.out.printf("%20s    %23s    %+9.2f ===%n", fixedMessage, t.getTime(), t.getAmount());
        }

        System.out.printf("============================================ Total: %8.2f ===%n", account.getBalance());
        System.out.println();
    }
}

