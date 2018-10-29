package com.pluralsight;


import java.io.Serializable;

public class BankAccount implements Serializable {

    private final String id;
    private int balance = 0;
    private char lastTxType;
    private int lastTxAmount;

    public BankAccount(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public BankAccount(String id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public synchronized void depostit(int amt){balance+=amt; System.out.println("Balance after deposit is "+balance);
    lastTxAmount = amt; lastTxType = 'd';}

    public synchronized void withdraw(int amt){balance-=amt; System.out.println("Balance after withdraw is "+balance);
        lastTxAmount = amt; lastTxType = 'w';}
}
