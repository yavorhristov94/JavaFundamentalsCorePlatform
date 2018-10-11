package com.pluralsight;
//not thread safe
 public class NonSafeAccount {
    private int balance;

    public NonSafeAccount(int startBalance){balance = startBalance;}

    public int getBalance(){return balance;}

    public void deposit(int amount){balance += amount;}
}
