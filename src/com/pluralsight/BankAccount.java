package com.pluralsight;

public class BankAccount {
    private int balance;
    private final String id;

    public BankAccount(String id) {
        this.id = id;
    }

    public BankAccount(int balance, String id) {
        this.balance = balance;
        this.id = id;
    }

    public synchronized int getBalance(){return balance;}

    public synchronized void deposit(int amount){balance += amount;}

    public synchronized void withdraw(int amount){balance -= amount;}
}
